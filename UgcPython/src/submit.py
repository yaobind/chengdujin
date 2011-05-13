#!/usr/local/bin/python


##
# submit.py serves:
# 1. binary data uploaded by users
# 2. saving to a unique local file
# 3. inserting the name to the db
# 4. returning the file path
#
#@author: Yuan Jin 
#@contact: jinyuan@baidu.com
#@since: April 20, 2011


import cgi
from DbOperation import DbOperation
import logging
import MySQLdb
import os
import random
import time
from xml.dom import minidom


##
# global variables
#
# Configuration file
config = minidom.parse('/Users/Yuan/Desktop/config.xml')

# Self-defined error codes
ERROR_CODE = {
             "4":"No file found.", 
             "3":"No file uploaded.",
             "2":"File of a wrong format.",
             "1":"File size larger than limit."
             }


## 
# insert the file suffix to the database
#
# pars: path string
# return: 
#
def copyToDb(path):
    try:
        do = DbOperation()
        db = do.dbConnect(SERVER, DATABASE, USER, PASSWORD)
        cur = db.cursor()
        # create the database if it isn't there
        do.dbCreate(cur, TABLE)
        do.dbInsert(cur, TABLE, path)
    except Exception, e:
        logging.info('[' + time.strftime('%X %x') + '] ' + str(e))
        raise e
        

##
# write the binary data to a unique file. it needs
# to check if the file system already has the file,
# and to check if db already has the file.
#
# the point to check db here is because before writ-
# ting to the file system, we are still able to cha-
# nge the file name if a duplicate is found. after 
# this, the cost to change the name on the file sys-
# tem is much greater.
#
# pars: pathPrefix string; pathSuffix string; data
# return: pathPrefix string
#
def writeToFile(pathPrefix, pathSuffix, data):
    # find duplicate
    hasDuplicate = True
    while hasDuplicate:
        hasDuplicate = False
        
        # check if the name is a duplicate on the file system
        if os.path.isfile(UPLOADED_DIRECTORY + pathPrefix + pathSuffix):
            logging.info('[' + time.strftime('%X %x') + '] ' + 'Find one duplicate on the file system: ' + (pathPrefix + pathSuffix))
            hasDuplicate = True
        
        # check if the name is a duplicate in db
        try:
            con = MySQLdb.connect(host = SERVER, db = DATABASE, user = USER, passwd = PASSWORD)
        except Exception, e:
            logging.info('[' + time.strftime('%X %x') + '] ' + str(e))
            raise e
        
        # connection is valid, then check the the name's availability
        if con:
            try:
                cur = con.cursor()
                cur.execute("SELECT fileName FROM %s.%s WHERE fileName=\'%s\'" % (DATABASE, TABLE, (pathPrefix + pathSuffix)))
                con.commit()
                res = cur.fetchone()
                if res:
                    logging.info('[' + time.strftime('%X %x') + '] ' + 'Find one duplicate in DB: ' + (pathPrefix + pathSuffix))
                    hasDuplicate = True
            except Exception, e:
                logging.info('[' + time.strftime('%X %x') + '] ' + str(e))
                raise e
            
        if hasDuplicate:
            pathPrefix = randomizeName(pathPrefix)        
        
    
    opf = open(UPLOADED_DIRECTORY + pathPrefix + pathSuffix, 'wb+')
    opf.write(data)
    opf.close()
    
    # return a correct randomized name
    name = pathPrefix + pathSuffix
    return name


## 
# randomize the file name with time
#
# pars: name string
# return: prefix string
#
def randomizeName(name):
    # get the complete form of current time (aaaaaabbbbbb), 
    # instead of aaaaaa.bbbbb
    newName = int(repr(time.time()).replace('.', ''))
    
    newName += ~(newName << random.randint(1, len(str(newName))))
    # mix the file name's ascii code into the prefix
    for ch in name:
        newName += ord(ch)
    newName ^= (newName >> random.randint(1, len(str(newName))))
    
    # shuffle for some random times
    newName = str(newName)
    runs = int(newName[random.randint(0, len(newName)-1)])
    while runs >=0:
        random.shuffle(list(newName))
        runs -= 1
    
    # if the length of the new name is longer than limit
    # , cut only a section of the new name
    if len(newName) > UPLOADED_NAME_LENGTH:
        newName = newName[:(UPLOADED_NAME_LENGTH - 1)]
        
    return newName


## 
# iterate all the data in the file object and,
# check its size before delivering it to the 
# next step
#
# pars: item file object; message string (?)
# return: msg string (?)
#
def receiveFile(item):
    file = ''
    # binary data as a list
    for data in item.file:
        file +=  data
            
    # avoid images larger than limit   
    if len(file) > UPLOADED_MAX_SIZE:
        logging.info('[' + time.strftime('%X %x') + '] ' + '1:' + ERROR_CODE['1'])
        raise Exception('1:' + ERROR_CODE['1'])
    
    return file
            

## 
# check the content-type section of the header of the request,
# for its type; check the suffix of the file name to avoid
# no-suffix files
#
# pars: item file object
# return: suffix string
#
def typeAndSuffixCheck(item):    
    # file suffix acceptable
    acptTypes = ['image/gif', 'image/jpeg', 'image/png']
    acptSuffix = ['gif', 'jpeg', 'jpg', 'png']
    
    # check if file is in a wrong format
    if not str(item.headers['content-type']) in acptTypes:
        logging.info('[' + time.strftime('%X %x') + '] ' + '2:' + ERROR_CODE['2'])
        raise Exception('2:' + ERROR_CODE['2'])
            
    # copy the file suffix for renaming
    # it's possible that the file name passes the content-type
    # checking, but it has no file suffix
    nameParts = item.headers['content-type'].split('/')
    
    if not (nameParts[len(nameParts)-1]).lower() in acptSuffix:
        logging.info('[' + time.strftime('%X %x') + '] ' + '2:' + ERROR_CODE['2'])
        raise Exception('2:' + ERROR_CODE['2']) 
    
    suffix = (nameParts[len(nameParts)-1]).lower()
    return suffix


## 
# create a local file for user's uploaded image
#
# pars:
# return: name string
#
def saveToLocal():
    form = cgi.FieldStorage()
    message = ''
    
    # get the data out from cgi
    if form.has_key(FORM_KEY):
        item = form[FORM_KEY]
        
        # check if nothing is uploaded
        if not item.filename:
            logging.info('[' + time.strftime('%X %x') + '] 3:' + ERROR_CODE['3'])
            raise Exception('3:' + ERROR_CODE['3'])
        else:
            # check the file type and its file name suffix
            suffix = typeAndSuffixCheck(item)
            
            # receive the file from binary data
            message = receiveFile(item)
            
            # randomize the file name
            name = os.path.basename(unicode(item.filename, UPLOADED_NAME_ENCODING).encode(UPLOADED_NAME_ENCODING))
            prefix = randomizeName(name)
            name = prefix + '.' + suffix
              
            # create a file for user's uploaded image
            try:
                name = writeToFile(prefix, '.' + suffix, message)
            except IOError:
                # give it another try, before reporting
                # probably it will get a new randomized name
                try:
                    name = writeToFile(prefix, '.' + suffix, message)
                except Exception, e:
                    logging.info('[' + time.strftime('%X %x') + '] ' + str(e))
                    raise e
    else:
        logging.info('[' + time.strftime('%X %x') + '] ' + '4:' + ERROR_CODE['4'])
        raise Exception('4:' + ERROR_CODE['4'])

    return name


## 
# distribute the tasks
#
# pars:
# return: html string
#
def procRequest():
    try:
        # save user's file to a unique local file
        filePath = saveToLocal()
        logging.info('[' + time.strftime('%X %x') + '] ' + UPLOADED_DIRECTORY + filePath + ' is created.')
        
        # update the file path to db
        copyToDb(filePath)
        logging.info('[' + time.strftime('%X %x') + '] ' + filePath + ' is inserted to table ' + TABLE + ' of database ' + SERVER + ':'+ DATABASE)
        
        print '{status: 1}'
        logging.info('[' + time.strftime('%X %x') + '] Service successfully delivered!')
    except Exception, e:
        return '{status: 0}'


## 
# load data from the configuration file
#
# pars: tag string
# return: data string
#
def readConfig(tag):
    refs = config.getElementsByTagName(tag)
    return refs[0].childNodes[0].data


##
# Constants
# Form key
FORM_KEY = readConfig('FormKey')

# Database
SERVER = readConfig('Server')
DATABASE = readConfig('Database')
TABLE = readConfig('Table')
USER = readConfig('User')
PASSWORD = readConfig('Password')

# File system
UPLOADED_DIRECTORY = readConfig('UploadedDirectory')
UPLOADED_MAX_SIZE = int(readConfig('UploadedMaxSize'))
UPLOADED_NAME_LENGTH = int(readConfig('UploadedNameLength'))
UPLOADED_NAME_ENCODING = readConfig('UploadedNameEncoding')
LOGGING_FILE = readConfig('LoggingFile')
            

if __name__ == '__main__':
    # start logging
    logging.basicConfig(filename=LOGGING_FILE, level=logging.DEBUG)
    logging.info('')
    logging.info('[' + time.strftime('%X %x') + '] Service started!')
    procRequest()
