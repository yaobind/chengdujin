##
# ImeJpException derives from Exception and, 
# serves as a simple home-made exception
#
#@author: Yuan Jin 
#@contact: jinyuan@baidu.com
#@since: May 5, 2011

## 
# ImeJpException Codes:
#
# code:explanation
#

import exceptions

errorCode = {
             "4":"No file found.", 
             "3":"No file uploaded.",
             "2":"File of a wrong format.",
             "1":"File size larger than limit."
             }

class ImeJpException(exceptions.Exception):
    def __init__(self):
        return
    
    def __str__(self):
        print '[' + self.code + ']:' + errorCode[self.code]