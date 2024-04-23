package in.practise.concurrency.semaphore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomSemaphore {
    private final ReentrantLock lock;

    private final Condition permitsCondition;
    private int permits;
    public CustomSemaphore(int permits){
        if(permits <= 0){
            throw new IllegalArgumentException("Permits must be positive");
        }
        this.permits = permits;
        lock = new ReentrantLock(true);
        permitsCondition = lock.newCondition();
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try{
           while(permits == 0){
               permitsCondition.await();
           }
           permits--;
        } finally {
            lock.unlock();
        }
    }

    public void release(){
        lock.lock();
        try{
            permits++;
            permitsCondition.signal();
        }finally {
            lock.unlock();
        }
    }
}
