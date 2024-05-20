package in.practise.concurrency.oddeven;

import java.util.concurrent.atomic.AtomicInteger;

public class OddEvenMain {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        Object lock = new Object();

        Printer oddPrinter = new Printer(1, 2, 20, lock, atomicInteger);
        Printer evenPrinter = new Printer(2, 2, 20, lock, atomicInteger);

        Thread t1 = new Thread(oddPrinter, "OddPrinter");
        Thread t2 = new Thread(evenPrinter, "EvenPrinter");
        t2.start();
        t1.start();
    }

     static class Printer implements Runnable{

        private int currentValue;
        private int step;
        private Object lock;
        private int maxValue;
        private AtomicInteger counter;

        public Printer(int startValue, int step, int maxValue, Object lock, AtomicInteger counter){
            this.currentValue =startValue;
            this.step = step;
            this.maxValue = maxValue;
            this.lock = lock;
            this.counter = counter;
        }

        @Override
        public void run() {
            synchronized (lock){
                while(counter.get() < maxValue){
                    while(counter.get() != currentValue ){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(Thread.currentThread().getName() +  " " + counter.get());
                    currentValue = counter.get() + step;
                    counter.getAndIncrement();
                    lock.notifyAll();
                }
            }
        }
    }
}
