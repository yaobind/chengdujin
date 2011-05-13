import MySQLdb
import logging
import time


##
# class dedicated to database operations
#
class DbOperation:
    def dbConnect(self, server, database, user, password):
        try:
            self.con = MySQLdb.connect(host = server, db = database, user = user, passwd = password)
        except Exception, e:
            try:
                self.con.query('CREATE DATABASE %s' % database)
                self.con.query("GRANT ALL ON %s.* to ''@'%s" % (database, server))
                self.con.commit()
                self.con.close()
                self.con = MySQLdb.connect(host = server, db = database, user = user, passwd = password)
            except Exception, e:
                raise e
        return self.con
    
    
    def dbCreate(self, cur, table):
        try:
            cur.execute("CREATE TABLE %s (fileName VARCHAR(80))" % table)
            self.con.commit()
            self.con.close()
        except Exception, e:
            logging.info('[' + time.strftime('%X %x') + '] ' + str(e))
            pass
    
    
    def dbInsert(self, cur, table, data):
        cur.execute("INSERT INTO %s VALUES (\'%s\')" % (table, data))
        self.con.commit()
        self.con.close()