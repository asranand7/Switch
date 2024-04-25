package in.practise.producerconsumer;

import in.practise.concurrency.blockingqueue.CustomArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProducerConsumerMain {
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean stopProduce = new AtomicBoolean(false);

        CustomArrayBlockingQueue<Long> buffer = new CustomArrayBlockingQueue<>(100);
        Runnable producer = new Producer(buffer, stopProduce);
        Runnable consumer = new Consumer(buffer, stopProduce);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
        Thread.sleep(1000);

        stopProduce.set(true);

        Thread.sleep(2000);
    }

    static class Producer implements Runnable{
        private final CustomArrayBlockingQueue<Long> queue;
        private final AtomicBoolean stopProduce;
        public Producer(CustomArrayBlockingQueue<Long> queue, AtomicBoolean stopProduceFlag){
            this.queue = queue;
            this.stopProduce = stopProduceFlag;
        }
        @Override
        public void run() {
            while(!stopProduce.get()){
                try {
                    queue.put(System.currentTimeMillis());

                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Producer thread stopped prodcing");
        }
    }

    static class Consumer implements Runnable{
        private final CustomArrayBlockingQueue<Long> queue;

        private final AtomicBoolean stopProduce;
        public Consumer(CustomArrayBlockingQueue<Long> queue, AtomicBoolean stopProduceFlag){
            this.queue = queue;
            this.stopProduce = stopProduceFlag;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    // Check if producer has stopped and the queue is empty
                    if (stopProduce.get() && queue.size() == 0) {
                        break;
                    }

                    // If the stop produce is set as true now then and the queue is empty then consumer will never stop as take is a blocking call.
                    System.out.println("Consumer consumed: " + queue.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Consumer thread stopped consuming");

        }
    }
}
