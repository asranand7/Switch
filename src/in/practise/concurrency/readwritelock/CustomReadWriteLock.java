package in.practise.concurrency.readwritelock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomReadWriteLock {
    private int readers;
    private int writers;
    private int writeReqeusts;
    private final ReentrantLock lock;
    private final Condition readCondition;
    private final Condition writeConditon;

    public CustomReadWriteLock() {
        this.lock = new ReentrantLock(true);
        readCondition = lock.newCondition();
        writeConditon = lock.newCondition();
    }

    public void acquireReadLock() throws InterruptedException {
        lock.lock();
        try {
            while (writers > 0 || writeReqeusts > 0) {
                readCondition.await();
            }
            readers++;
        } finally {
            lock.unlock();
        }
    }

    public void releaseReadLock() throws InterruptedException, IllegalAccessException {
        lock.lock();
        try {
            if (readers == 0) throw new IllegalAccessException("Readers count is already zero");
            readers--;
            if (readers == 0) {
                writeConditon.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void acquireWriteLock() throws InterruptedException {
        lock.lock();
        try {
            writeReqeusts++;
            while (writers > 0 || readers > 0) {
                writeConditon.await();
            }
            writeReqeusts--;

            writers++;
        } finally {
            lock.unlock();
        }
    }

    public void releaseWriteLock() throws InterruptedException, IllegalAccessException {
        lock.lock();
        try {
            if (writers == 0) throw new IllegalAccessException("Writers count is already zero");
            writers--;
            readCondition.signalAll();
            writeConditon.signal();
        } finally {
            lock.unlock();
        }
    }
}
