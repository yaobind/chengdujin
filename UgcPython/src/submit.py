#!/usr/local/bin/python
# -*- coding: utf-8 -*-


##
# submit.py serves:
# 1. binary data uploaded by users
# 2. saving to a unique local file
# 3. compressing the skin files
# 4. inserting the name to the db
# 5. returning the file name
#
#@author: Yuan JIN
#@contact: jinyuan@baidu.com
#@since: April 20, 2011
#@latest: May 18, 2011

# TODO read_config
# TODO encoding checking


from DbOperation import DbOperation
import MySQLdb
import cgi
import codecs
import logging
import os
import random
import shutil
import time
import xml.dom.minidom
import zipfile


##
# global variables
#
# Configuration file
config = xml.dom.minidom.parse('/Users/Yuan/Desktop/config.xml')

# Self-defined error codes
ERROR_CODE = {
              "8": "File manipulation with zipfile goes wrong.", 
              "7":"Item in configuration is null.",
              "6":"Cannot find the skin template directory.",
              "5":"File bearing no skin index number.",
              "4":"No file found or cannot be interpreted.", 
              "3":"No file uploaded or bearing with no name.",
              "2":"File of a wrong format or being incorrectly formatted.",
              "1":"File size larger than the limit 2MB." }


## 
# insert the file name suffix to the database
#
# pars: path string
# return: 
#
def copy_to_database(path):
    try:
        do = DbOperation()
        db = do.dbConnect(SERVER, DATABASE, USER, PASSWORD)
        cur = db.cursor()
        # create the database if it isn't there
        do.dbCreate(cur, TABLE, COLUMN1)
        do.dbInsert(cur, TABLE, COLUMN1, path)
    except Exception, e:
        raise e

## 
# create the zipped along against the saved file, 
# and collect other necessary skin files from the
# skin_directory
#
# pars: sin string; path string; suffix string
# return: n/a
#
def compress_files(sin, path, suffix):
    # locate the specific skin template directory
    skin_directory = SKIN_TEMPLATE_DIRECTORY + SKIN_TEMPLATE_DIR_NAME + sin
    
    # check if the skin directory exists
    if os.path.isdir(skin_directory):
        # create the zipped file sharing the same name
        # as the dtt file
        zipped_file_path = UPLOADED_DIRECTORY + path + '.' + COMPRESSED_SKIN_PACKAGE_SUFFIX
        try :
            zipped = zipfile.ZipFile(zipped_file_path, 'w')
        
            # include the transferred personal skin file first
            zipped.write(UPLOADED_DIRECTORY + path + os.sep + DTT_FILE_NAME + '.' + suffix, 
                     DTT_FILE_NAME + '.' + suffix)
            # and then the local Skin.xml file
            zipped.write(UPLOADED_DIRECTORY + path + os.sep + SKIN_CONFIGURATION_FILE, 
                     SKIN_CONFIGURATION_FILE)
            
            # include all the files in the template
            # directory, except Skin.xml
            for d in os.listdir(skin_directory):
                if d != SKIN_CONFIGURATION_FILE:
                    zipped.write(skin_directory + os.sep + d, d)
            
            zipped.close()
        except RuntimeError:
            raise Exception('[Error 8]:' + ERROR_CODE['8'])
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
# pars: dir_path string; 
#       file_suffix string; 
#       data unknown; 
#       skin integer;
# return: n/a
#
def write_to_file(dir_path, file_suffix, data, sin):
    # find duplicate
    has_duplicate = True
    
    while has_duplicate:
        has_duplicate = False
        
        # check if the name is a duplicate on the file system
        if os.path.isdir(UPLOADED_DIRECTORY + dir_path):
            logging.info('[' + time.strftime('%X %x') + '] ' + 'Find one duplicate on the file system: ' + dir_path)
            has_duplicate = True 
        
        # check if the name is a duplicate in the database
        # if a dup is already found on the file system,
        # save the time for checking the database
        if not has_duplicate:
            try:
                con = MySQLdb.connect(host = SERVER, db = DATABASE, user = USER, passwd = PASSWORD)
            except Exception, e:
                raise e 
        
            # connection is valid, then check the the name's availability
            if con:
                try:
                    cur = con.cursor()
                    cur.execute("SELECT %s FROM %s.%s WHERE %s=\'%s\'" % (COLUMN1, DATABASE, TABLE, COLUMN1, dir_path))
                    con.commit()
                    res = cur.fetchone() 
                    # holy shoot! we got a dup in the database!
                    if res:
                        logging.info('[' + time.strftime('%X %x') + '] ' + 'Find one duplicate in DB: ' + dir_path)
                        has_duplicate = True
                except Exception, e:
                    raise e  
                
        # we found a dup either on the file system, or in the database    
        if has_duplicate:
            dir_path = randomize_name(dir_path)
    
    # create the directory
    os.mkdir(UPLOADED_DIRECTORY + dir_path)
        
    # create the file dans le repertoire
    file_name = UPLOADED_DIRECTORY + dir_path + os.sep + DTT_FILE_NAME + '.' + file_suffix
    opf = open(file_name, 'wb')
    opf.write(data)
    opf.close()
    
    # copy skin.xml from skin_template_directory 
    # to new directory
    srcPath = SKIN_TEMPLATE_DIRECTORY + SKIN_TEMPLATE_DIR_NAME + str(sin) + os.sep + SKIN_CONFIGURATION_FILE
    dstPath = UPLOADED_DIRECTORY + dir_path
    shutil.copy(srcPath, dstPath)
    
    # update the skin_configuration_file
    local_config_file = UPLOADED_DIRECTORY + dir_path + os.sep + SKIN_CONFIGURATION_FILE
    doc = xml.dom.minidom.parse(local_config_file)
    root = doc.getElementsByTagName(SKIN_CONFIG_ELEMENT)[0]
    root.setAttribute(SKIN_CONFIG_ATTRIBUTE, dir_path)
    f = open(local_config_file, 'w')
    # encoding: utf-8, or else by default: ascii
    f = codecs.lookup('utf_8')[-1](f)
    doc.writexml(f)
    f.close()

## 
# randomize the file name with time
#
# pars: name string
# return: new_name string
#
def randomize_name(name):
    # get the complete form of current time (aaaaaabbbbbb), 
    # instead of aaaaaa.bbbbb
    new_name = int(repr(time.time()).replace('.', ''))
    
    new_name += ~(new_name << random.randint(1, len(str(new_name))))
    # mix the file name's ascii code into the prefix
    for ch in name:
        new_name += ord(ch)
    new_name ^= (new_name >> random.randint(1, len(str(new_name))))
    
    # shuffle for some random times
    new_name = str(new_name)
    runs = int(new_name[random.randint(0, len(new_name)-1)])
    while runs >=0:
        random.shuffle(list(new_name))
        runs -= 1
    
    # if the length of the new name is longer than limit
    # , cut only a section of the new name
    if len(new_name) > UPLOADED_NAME_LENGTH:
        new_name = new_name[:(UPLOADED_NAME_LENGTH - 1)]
        
    return new_name

## 
# iterate all the data in the file object and,
# check its size before delivering it to the 
# next step
#
# pars: item file object; message string (?)
# return: file_data string (?)
#
def read_file_data(item):
    file_data = ''
    # binary data as a list
    for data in item.file:
        file_data +=  data
            
    # avoid images larger than limit   
    if len(file_data) > UPLOADED_MAX_SIZE:
        raise Exception('[Error 1]:' + ERROR_CODE['1'])
    
    return file_data

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
# return: skin_index integer
#
def get_skin_index_number(item):
    try: 
        name_parts = item.filename.split('_')
        # int() is to do a simple check here for null values
        # TODO need more complicated check
        skin_index = int(name_parts[0])
        return skin_index
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
def check_type_and_suffix(item):
    # TODO rewrite this part
    # This is a low level danger to ignore checking content-type
    # check if file is in a wrong format
    # this seems to be wrong with the actual content-type
    # commented for now
#    acptTypes = ['image/png', 'image/jpeg', 'image/gif']    
#    if not str(item.headers['content-type']) in acptTypes:
#        raise Exception('[Error 2]:' + ERROR_CODE['2'])
    
    # acceptable file suffix
    acpt_suffix = ['png', 'jpeg', 'jpg', 'gif']
    
    # copy the file suffix for renaming
    # it's possible that the file name passes the content-type
    # checking, but it has no file suffix
    name_parts = item.filename.split('.')
    
    if not (name_parts[len(name_parts)-1]).lower() in acpt_suffix:
        raise Exception('[Error 2]:' + ERROR_CODE['2']) 
    
    suffix = (name_parts[len(name_parts)-1]).lower()
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
# return: dir_name string;
#         file_name_suffix string;
#         skin_index_number integer
#
def save_to_server():
    # read from cgi's storage
    form = cgi.FieldStorage()
    
    # get the data out from cgi
    if form.has_key(FORM_KEY):
        item = form[FORM_KEY]
        
        # check if nothing is uploaded
        # a dangerous trick
        if not item.filename:
            raise Exception('[Error 3]:' + ERROR_CODE['3'])
        else:
            # check the file type and its file name suffix
            file_name_suffix = check_type_and_suffix(item)
            
            # get the skin index number from the file name
            skin_index_number = get_skin_index_number(item)
            
            # read the file from binary data
            file_data = read_file_data(item)
            
            # encoding: this might cause problems
            name = os.path.basename(unicode(item.filename, UPLOADED_NAME_ENCODING).encode(UPLOADED_NAME_ENCODING))
            # randomize the directory name
            dir_name = randomize_name(name)
              
            # create a file on the server
            try:
                write_to_file(SKIN_TEMPLATE_DIR_NAME + str(skin_index_number) + '_' + dir_name, file_name_suffix, file_data, skin_index_number)
                return (SKIN_TEMPLATE_DIR_NAME + str(skin_index_number) + '_' + dir_name), file_name_suffix, skin_index_number
            except IOError:
                # give it another try, before reporting
                # probably it will get a new randomized name
                try:
                    write_to_file(SKIN_TEMPLATE_DIR_NAME + str(skin_index_number) + '_' + dir_name, file_name_suffix, file_data, skin_index_number)
                    return (SKIN_TEMPLATE_DIR_NAME + str(skin_index_number) + '_' + dir_name), file_name_suffix, skin_index_number
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
def main():
    try:
        # save user's file to a unique local file
        # dtt is short for da-tou-tie
        dtt_directory_name, dtt_file_suffix, skin_index_number = save_to_server()
        logging.info('[' + time.strftime('%X %x') + '] ' + UPLOADED_DIRECTORY + dtt_directory_name + os.sep + DTT_FILE_NAME + '.' + dtt_file_suffix + ' is created.')
        logging.info('[' + time.strftime('%X %x') + '] Skin Index Number ' + str(skin_index_number) + ' is found.')
        
        # zip the file with other skin files to skin_package_suffix
        compress_files(str(skin_index_number), dtt_directory_name, dtt_file_suffix)
        logging.info('[' + time.strftime('%X %x') + '] ' + UPLOADED_DIRECTORY + dtt_directory_name + '.' + COMPRESSED_SKIN_PACKAGE_SUFFIX + ' is created.')
        
        # update the file path to the database
        copy_to_database(dtt_directory_name)
        logging.info('[' + time.strftime('%X %x') + '] ' + dtt_directory_name + ' is inserted to table ' + TABLE + ' of database ' + SERVER + ':'+ DATABASE)
        
        # echo to the requester
        print HEADER + (dtt_directory_name)
        logging.info('[' + time.strftime('%X %x') + '] Service successfully delivered!')
    except Exception, e:
        print HEADER + ('0')
        logging.info('[' + time.strftime('%X %x') + '] ' + str(e))

## 
# load constant values from the configuration file
#
# personally, I prefer placing constants in the 
# the same source file, avoiding any cost brought 
# by reading other files
#
# pars: tag string
# return: ret string
#
def read_config(tag):
    try:
        refs = config.getElementsByTagName(tag)
        ret = refs[0].childNodes[0].data
        if len(ret) == 0:
            Exception('[Error 7]:' + ERROR_CODE['7'])
        else:
            return ret
    except Exception, e:
        # config.xml might be deprecated
        logging.info('[' + time.strftime('%X %x') + '] ' + str(e))
        raise e


##
# Constants
#
# For more info, check config.xml

# Form key
FORM_KEY = read_config('FormKey')
HEADER = 'Content-Type: text/html \n\n'

# Database
SERVER = read_config('Server')
DATABASE = read_config('Database')
TABLE = read_config('Table')
COLUMN1 = read_config('Column1')
USER = read_config('User')
PASSWORD = read_config('Password')

# File system
LOGGING_FILE = read_config('LoggingFile')

UPLOADED_DIRECTORY = read_config('UploadedDirectory')
UPLOADED_MAX_SIZE = int(read_config('UploadedMaxSize'))
UPLOADED_NAME_LENGTH = int(read_config('UploadedNameLength'))
UPLOADED_NAME_ENCODING = read_config('UploadedNameEncoding')

# directory where skin templates are located
SKIN_TEMPLATE_DIRECTORY = read_config('SkinTemplateDirectory')
SKIN_TEMPLATE_DIR_NAME = read_config('SkinTemplateDirName')
SKIN_CONFIGURATION_FILE = read_config('SkinConfigurationFile')
SKIN_CONFIG_ELEMENT = read_config('SkinConfigElement')
SKIN_CONFIG_ATTRIBUTE = read_config('SkinConfigAttribute')
# e.g. candv
DTT_FILE_NAME = read_config('DttFileName')
# e.g. bts, n.b. without '.'
COMPRESSED_SKIN_PACKAGE_SUFFIX = read_config('CompressedSkinPackageSuffix')
            

if __name__ == '__main__':
    # start logging
    logging.basicConfig(filename=LOGGING_FILE, level=logging.DEBUG)
    logging.info('')
    logging.info('[' + time.strftime('%X %x') + '] Service started!')
    main()
