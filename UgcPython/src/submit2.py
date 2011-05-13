#!/usr/local/bin/python


##
# submit.py serves:
# 1. binary data uploaded by users
# 2. saving to a unique local file
# 3. zipping the skin files
# 4. inserting the name to the db
# 5. returning the file path
#
#@author: Yuan JIN
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
config = minidom.parse('/home/work/yuanj/config.xml')

# Self-defined error codes
ERROR_CODE = {
              "5":"[Error] File bearing no Skin Index Number",
              "4":"[Error] No file found or cannot be interpreted.", 
              "3":"[Error] No file uploaded or bearing with no name.",
              "2":"[Error] File of a wrong format or being incorrectly formatted.",
              "1":"[Error] File size larger than the limit 2MB." }


## 
# insert the file suffix to the database
#
# pars: path string
# return: 
#
def copyToDatabase(path):
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
def readFile(item):
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
# get the skin index number from the 
# file name itself. by default, the file
# name is composed as: 
#
# 'skinIndexNumber_filename.png'
#
# n.b. the suffix of the file name
# is subject to potential changes.
#
# pars: item file object
# return: skinIndex integer
#
def getSkinIndexNumber(item):
    try: 
        nameParts = item.filename.split('_')
        skinIndex = int(nameParts[0])
        return skinIndex
    except Exception:
        raise Exception('5:' + ERROR_CODE['5'])  


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
    acptTypes = ['image/png', 'image/jpeg', 'image/gif']
    acptSuffix = ['png', 'jpeg', 'jpg', 'gif']
    
    # check if file is in a wrong format
    if not str(item.headers['content-type']) in acptTypes:
        raise Exception('2:' + ERROR_CODE['2'])
            
    # copy the file suffix for renaming
    # it's possible that the file name passes the content-type
    # checking, but it has no file suffix
    nameParts = item.headers['content-type'].split('/')
    
    if not (nameParts[len(nameParts)-1]).lower() in acptSuffix:
        raise Exception('2:' + ERROR_CODE['2']) 
    
    suffix = (nameParts[len(nameParts)-1]).lower()
    return suffix


## 
# create a local file on server for user's
# uploaded image
#
# this method involves type checking, retrie-
# ving the skin index number, randomization of
# the file name, as well as checking the dup-
# licates before writing to the file system and 
# the database
#
# pars: n/a
# return: name string
#
def saveToServer():
    # read from cgi's storage
    form = cgi.FieldStorage()
    
    # get the data out from cgi
    if form.has_key(FORM_KEY):
        file = form[FORM_KEY]
        
        # check if nothing is uploaded
        if not file.filename:
            raise Exception('3:' + ERROR_CODE['3'])
        else:
            # check the file type and its file name suffix
            fileNameSuffix = typeAndSuffixCheck(file)
            
            # get the skin index number from the file name
            skinIndexNumber = getSkinIndexNumber(file)
            
            # read the file from binary data
            fileData = readFile(file)
            
            # randomize the file name
            name = os.path.basename(unicode(file.filename, UPLOADED_NAME_ENCODING).encode(UPLOADED_NAME_ENCODING))
            prefix = randomizeName(name)
            name = prefix + '.' + fileNameSuffix
              
            # create a file for user's uploaded image
            try:
                name = writeToFile(prefix, '.' + fileNameSuffix, fileData)
            except IOError:
                # give it another try, before reporting
                # probably it will get a new randomized name
                try:
                    name = writeToFile(prefix, '.' + fileNameSuffix, fileData)
                    return name
                except Exception, e:
                    raise e
    else:
        raise Exception('4:' + ERROR_CODE['4'])


## 
# distribute the tasks - it is the actual
# main entry to the program
#
# pars: n/a
# return: html string
#
def processRequest():
    try:
        # save user's file to a unique local file
        filePathOnServer = saveToServer()
        logging.info('[' + time.strftime('%X %x') + '] ' + UPLOADED_DIRECTORY + filePathOnServer + ' is created.')
        
        # zip the file with other skin files to .bts
        
        # update the file path to the database
        copyToDatabase(filePathOnServer)
        logging.info('[' + time.strftime('%X %x') + '] ' + filePathOnServer + ' is inserted to table ' + TABLE + ' of database ' + SERVER + ':'+ DATABASE)
        
        # echo to the requester
        logging.info('[' + time.strftime('%X %x') + '] Service successfully delivered!')
        print UPLOADED_DIRECTORY + filePathOnServer
    except Exception, e:
        logging.info('[' + time.strftime('%X %x') + '] ' + str(e))
        print '-1'


## 
# load constant values from the configuration file
#
# personally, I prefer placing constants in the 
# the same source file, avoiding any cost brought 
# by reading other files
#
# pars: tag string
# return: data string
#
def readConfig(tag):
    refs = config.getElementsByTagName(tag)
    return refs[0].childNodes[0].data


##
# Constants
#
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
    processRequest()
