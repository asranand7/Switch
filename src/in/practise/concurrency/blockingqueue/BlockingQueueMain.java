package in.practise.concurrency.blockingqueue;


public class BlockingQueueMain {
    public static void main(String[] args) throws InterruptedException {
        CustomArrayBlockingQueue<String> customArrayBlockingQueue = new CustomArrayBlockingQueue<>(5);
        Thread producer = new Thread(() -> {
            while(true){
                long timestamp = System.currentTimeMillis();
                try {
                    customArrayBlockingQueue.put("" + timestamp);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }

            }
        });

        Thread consumer1 = new Thread(() -> {
            while(true){
                try {
                    System.out.println("Consumer 1 Take " + customArrayBlockingQueue.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumer2 = new Thread(() -> {
            while(true){
                try {
                    System.out.println("Consumer 2 Take " + customArrayBlockingQueue.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        Thread consumer3 = new Thread(() -> {
            while(true){
                try {
                    System.out.println("Consumer 3 Take " + customArrayBlockingQueue.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        producer.start();
        Thread.sleep(100);
        consumer1.start();
        consumer2.start();
        consumer3.start();


    }
}
