#!/usr/local/bin/python

import time
import MySQLdb
import logging


SERVER = 'localhost'
DATABASE = 'test'
TABLE = 'SubmittedFiles'
USER = 'root'
PASS = 'root'

# File system
DIR = '/home/work/yuanj/images/'
LOGGING_FILE = '/home/work/yuanj/submit.log'


def copyToDb(path):
    try:
        con = MySQLdb.connect(host = SERVER, db = DATABASE, user = USER, passwd = PASS)
        cur = con.cursor()
        cur.execute("INSERT INTO SubmittedFiles VALUES (123123123)")
    except Exception, e:
        logging.info('[' + time.strftime('%X %x %Z') + '] ' + str(e))
        raise e
        

if __name__ == '__main__':
    # start logging
    logging.basicConfig(filename=LOGGING_FILE, level=logging.DEBUG)
    logging.info('startng ....')
    copyToDb("well, this is a test.")
    logging.info('terminated')
