package in.practise.concurrency.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomArrayBlockingQueue<T> {
    private T[] array;
    private int head;
    private int tail;
    private final int capacity;
    private int size;

    private  final ReentrantLock lock;
    private final Condition fullCondition;
    private final Condition emptyCondition;

    public CustomArrayBlockingQueue(int capacity){
        head = 0;
        tail = 0;
        size = 0;
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
        lock = new ReentrantLock(true);
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
    }

    public void put(T element) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while(size == capacity){
                fullCondition.await();
            }
            if(tail == capacity){
                tail = 0;
            }
            array[tail] = element;
            tail++;
            size++;
            emptyCondition.signal();
        }finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        T element;
        lock.lockInterruptibly();
        try{
            while(size == 0){
                emptyCondition.await();
            }
            if(head == capacity){
                head = 0;
            }
            element = array[head];
            array[head] = null;
            size--;
            head++;
            fullCondition.signal();
        } finally {
            lock.unlock();
        }
        return element;

    }
}
