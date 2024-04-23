package in.practise.concurrency.semaphore;

import java.util.concurrent.Semaphore;

public class CustomSemaphoreMain {
    public static void main(String[] args) throws InterruptedException {
        CustomSemaphore semaphore = new CustomSemaphore(1);

        Thread ping = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(5);
                    semaphore.acquire();
                    System.out.println("Acquired " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
            }
        });

        Thread pong = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(5);
                    semaphore.release();
                    System.out.println("Released " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                }
            } catch (Exception e) {

            }
        });

        ping.start();
        pong.start();
        ping.join();
        pong.join();

    }
}
