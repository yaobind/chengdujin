#!/usr/local/bin/python


##
# submit.py serves:
# 1. binary data uploaded by users
# 2. saving to a unique local file
# 3. zipping the skin files
# 4. inserting the name to the db
# 5. cleaning up 
# 6. returning the file path
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
import shutil
import time
from xml.dom import minidom
import zipfile


##
# global variables
#
# Configuration file
config = minidom.parse('/Users/Yuan/Desktop/config.xml')

# Self-defined error codes
ERROR_CODE = {
              "6":"Cannot find the skin template directory.",
              "5":"File bearing no skin index number.",
              "4":"No file found or cannot be interpreted.", 
              "3":"No file uploaded or bearing with no name.",
              "2":"File of a wrong format or being incorrectly formatted.",
              "1":"File size larger than the limit 2MB." }


##
# remove the unnecessary dtt directory, as
# well as the dtt file in that directory.
#
# n.b. rmtree will fail if there are read-
# only files
#
# pars: dir string
# return n/a
#
def cleanUp(dir):
    shutil.rmtree(dir)

## 
# insert the file name suffix to the database
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
        raise e
    

## 
# create the zipped along against the saved file, 
# and collect other necessary skin files from the
# skin_directory
#
# pars: sin string; path string
# return: n/a
#
def compressFiles(sin, path, suffix):
    # locate the specific skin template directory
    skinDirectory = SKIN_TEMPLATE_DIR_PATH + SKIN_TEMPLATE_DIR_PREFIX + sin
    
    # check if the skin directory exists
    if os.path.isdir(skinDirectory):
        # create the zipped file sharing the same name
        # as the dtt file
        zippedFilePath = UPLOADED_DIRECTORY + path + '.' + COMPRESSED_SKIN_PACKAGE_SUFFIX
        zipped = zipfile.ZipFile(zippedFilePath, 'w')
    
        # all files are compressed in the directory
        # skin_template_directory
        
        # include the transferred personal skin file first
        zipped.write(UPLOADED_DIRECTORY + path + os.sep + DTT_FILE_NAME + '.' + suffix, 
                     COMPRESSED_SKIN_DIRECTORY + os.sep + DTT_FILE_NAME + '.' + suffix)
        # include all the files in the template
        # directory
        for d in os.listdir(skinDirectory):
            zipped.write(skinDirectory + os.sep + d, COMPRESSED_SKIN_DIRECTORY + os.sep + d)
            
        zipped.close()
    else:
        raise Exception('[Error 6]:' + ERROR_CODE['6'])
        

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
# return: n/a
#
def writeToFile(dir, fileSuffix, data):
    # find duplicate
    hasDuplicate = True
    
    while hasDuplicate:
        hasDuplicate = False
        
        # check if the name is a duplicate on the file system
        if os.path.isdir(UPLOADED_DIRECTORY + dir):
            logging.info('[' + time.strftime('%X %x') + '] ' + 'Find one duplicate on the file system: ' + dir)
            hasDuplicate = True
        
        # check if the name is a duplicate in the database
        # if a dup is already found on the file system,
        # save the time for checking the database
        if not hasDuplicate:
            try:
                con = MySQLdb.connect(host = SERVER, db = DATABASE, user = USER, passwd = PASSWORD)
            except Exception, e:
                raise e
        
            # connection is valid, then check the the name's availability
            if con:
                try:
                    cur = con.cursor()
                    cur.execute("SELECT fileName FROM %s.%s WHERE fileName=\'%s\'" % (DATABASE, TABLE, dir))
                    con.commit()
                    res = cur.fetchone()
                    # holy shoot! we got a dup in the database!
                    if res:
                        logging.info('[' + time.strftime('%X %x') + '] ' + 'Find one duplicate in DB: ' + dir)
                        hasDuplicate = True
                except Exception, e:
                    raise e
        # we found a dup either on the file system, or in the database    
        if hasDuplicate:
            dir = randomizeName(dir)
    
    # create the directory
    os.mkdir(UPLOADED_DIRECTORY + dir)       
        
    # create the file dans le repertoire
    fileName = UPLOADED_DIRECTORY + dir + os.sep + DTT_FILE_NAME + '.' + fileSuffix
    opf = open(fileName, 'wb')
    opf.write(data)
    opf.close()


## 
# randomize the file name with time
#
# pars: name string
# return: newName string
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
    fileData = ''
    # binary data as a list
    for data in item.file:
        fileData +=  data
            
    # avoid images larger than limit   
    if len(fileData) > UPLOADED_MAX_SIZE:
        logging.info('[' + time.strftime('%X %x') + '] ' + '1:' + ERROR_CODE['1'])
        raise Exception('[Error 1]:' + ERROR_CODE['1'])
    
    return fileData


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
        # int() is to do a simple check here for null values
        skinIndex = int(nameParts[0])
        return skinIndex
    except Exception:
        raise Exception('[Error 5]:' + ERROR_CODE['5'])  


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
        raise Exception('[Error 2]:' + ERROR_CODE['2'])
            
    # copy the file suffix for renaming
    # it's possible that the file name passes the content-type
    # checking, but it has no file suffix
    nameParts = item.headers['content-type'].split('/')
    
    if not (nameParts[len(nameParts)-1]).lower() in acptSuffix:
        raise Exception('[Error 2]:' + ERROR_CODE['2']) 
    
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
# return: fileNamePrefix string;
#         fileNameSuffix string;
#         skinIndexNumber integer
#
def saveToServer():
    # read from cgi's storage
    form = cgi.FieldStorage()
    
    # get the data out from cgi
    if form.has_key(FORM_KEY):
        file = form[FORM_KEY]
        
        # check if nothing is uploaded
        if not file.filename:
            raise Exception('[Error 3]:' + ERROR_CODE['3'])
        else:
            # check the file type and its file name suffix
            fileNameSuffix = typeAndSuffixCheck(file)
            
            # get the skin index number from the file name
            skinIndexNumber = getSkinIndexNumber(file)
            
            # read the file from binary data
            fileData = readFile(file)
            
            # encoding: this might cause problems
            name = os.path.basename(unicode(file.filename, UPLOADED_NAME_ENCODING).encode(UPLOADED_NAME_ENCODING))
            # randomize the directory name
            dirName = randomizeName(name)
              
            # create a file on the server
            try:
                writeToFile(dirName, fileNameSuffix, fileData)
                return dirName, fileNameSuffix, skinIndexNumber
            except IOError:
                # give it another try, before reporting
                # probably it will get a new randomized name
                try:
                    writeToFile(dirName, fileNameSuffix, fileData)
                    return dirName, fileNameSuffix, skinIndexNumber
                except Exception, e:
                    raise e
    else:
        raise Exception('[Error 4]:' + ERROR_CODE['4'])


## 
# distribute the tasks - it is the actual
# main entry to the program
#
# pars: n/a
# return: n/a
#
def processRequest():
    try:
        # save user's file to a unique local file
        # dtt is short for da-tou-tie
        dttDirectoryName, dttFileSuffix, skinIndexNumber = saveToServer()
        logging.info('[' + time.strftime('%X %x') + '] ' + UPLOADED_DIRECTORY + dttDirectoryName + os.sep + DTT_FILE_NAME + '.' + dttFileSuffix + ' is created.')
        logging.info('[' + time.strftime('%X %x') + '] Skin Index Number ' + str(skinIndexNumber) + ' is found.')
        
        # zip the file with other skin files to skin_package_suffix
        compressFiles(str(skinIndexNumber), dttDirectoryName, dttFileSuffix)
        logging.info('[' + time.strftime('%X %x') + '] ' + UPLOADED_DIRECTORY + dttDirectoryName + '.' + COMPRESSED_SKIN_PACKAGE_SUFFIX + ' is created.')
        
        # update the file path to the database
        copyToDatabase(dttDirectoryName)
        logging.info('[' + time.strftime('%X %x') + '] ' + dttDirectoryName + ' is inserted to table ' + TABLE + ' of database ' + SERVER + ':'+ DATABASE)
        
        # remove the dtt directory and the dtt file
        cleanUp(UPLOADED_DIRECTORY + dttDirectoryName)
        logging.info('[' + time.strftime('%X %x') + '] ' + UPLOADED_DIRECTORY + dttDirectoryName + ' is removed.')
        
        # echo to the requester
        print UPLOADED_DIRECTORY + dttDirectoryName + '.' + COMPRESSED_SKIN_PACKAGE_SUFFIX
        logging.info('[' + time.strftime('%X %x') + '] Service successfully delivered!')
    except Exception, e:
        print '-1'
        logging.info('[' + time.strftime('%X %x') + '] ' + str(e))


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
    try:
        refs = config.getElementsByTagName(tag)
        return refs[0].childNodes[0].data
    except Exception, e:
        logging.info('[' + time.strftime('%X %x') + '] ' + str(e))
        raise e


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
LOGGING_FILE = readConfig('LoggingFile')

UPLOADED_DIRECTORY = readConfig('UploadedDirectory')
UPLOADED_MAX_SIZE = int(readConfig('UploadedMaxSize'))
UPLOADED_NAME_LENGTH = int(readConfig('UploadedNameLength'))
UPLOADED_NAME_ENCODING = readConfig('UploadedNameEncoding')

# directory where skin templates are located
SKIN_TEMPLATE_DIR_PATH = readConfig('SkinTemplateDirPath')
SKIN_TEMPLATE_DIR_PREFIX = readConfig('SkinTemplateDirPrefix')
# e.g. candv
DTT_FILE_NAME = readConfig('DttFileName')
# e.g. bts, n.b. without '.'
COMPRESSED_SKIN_PACKAGE_SUFFIX = readConfig('CompressedSkinPackageSuffix')
# directory name where all skin files are grouped
COMPRESSED_SKIN_DIRECTORY = readConfig('CompressedSkinDirectory')
            

if __name__ == '__main__':
    # start logging
    logging.basicConfig(filename=LOGGING_FILE, level=logging.DEBUG)
    logging.info('')
    logging.info('[' + time.strftime('%X %x') + '] Service started!')
    processRequest()
