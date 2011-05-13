import Queue, sys
from threading import Thread
 


class Worker(Thread):
    worker_count = 0
    timeout = 1
    
    def __init__(self, workQueue, resultQueue, **kwds):
        Thread.__init__(self, **kwds)
        self.id = Worker.worker_count
        Worker.worker_count += 1
        self.setDaemon(True)
        self.workQueue = workQueue
        self.resQueue = resultQueue
        self.start()
 
    def run(self):
        while True:
            try:
                callable, args, kwds = self.workQueue.get(timeout=Worker.timeout)
                res = callable(*args, **kwds)
                print "worker[%2d]: %s" % (self.id, str(res))
                self.resQueue.put(res)
            except Queue.Empty:
                break
            except :
                print 'worker[%2d]' % self.id, sys.exc_info()[:2]
                raise
              
              
class WorkerManager:
    def __init__(self, numOfWorkers=10, timeout = 2):
        self.workQueue = Queue.Queue()
        self.resQueue = Queue.Queue()
        self.workers = []
        self.timeout = timeout
        self.recruit(numOfWorkers)
 
 
    def recruit(self, num_of_workers):
        for i in range(num_of_workers):
            worker = Worker( self.workQueue, self.resQueue )
            self.workers.append(worker)
 
 
    def wait(self):
        while len(self.workers):
            worker = self.workers.pop()
            worker.join( )
            if worker.isAlive() and not self.workQueue.empty():
                self.workers.append(worker)
        print "All jobs are are completed."
 
 
    def addJob(self, callable, *args, **kwds):
        self.workQueue.put((callable, args, kwds))