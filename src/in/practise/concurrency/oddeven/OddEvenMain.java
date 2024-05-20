package in.practise.concurrency.oddeven;

import java.util.concurrent.atomic.AtomicInteger;

public class OddEvenMain {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        Object lock = new Object();

        Runnable oddPrinter = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (atomicInteger.get() < 20) {
                        while (atomicInteger.get() % 2 == 0) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("Odd " + atomicInteger.getAndIncrement());
                        lock.notifyAll();
                    }
                }
            }
        };

        Runnable evenPrinter = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (atomicInteger.get() <= 20) {
                        while (atomicInteger.get() % 2 == 1) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("Even " + atomicInteger.getAndIncrement());
                        lock.notifyAll();
                    }
                }
            }
        };

        Thread t1 = new Thread(oddPrinter);
        Thread t2 = new Thread(evenPrinter);
       t2.start();
        t1.start();
    }
}
