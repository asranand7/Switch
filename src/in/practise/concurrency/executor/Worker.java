package in.practise.concurrency.executor;

import java.util.concurrent.ArrayBlockingQueue;

public class Worker implements Runnable{
    ArrayBlockingQueue<Runnable> taskQueue;
    public Worker(ArrayBlockingQueue<Runnable> queue){
        taskQueue = queue;
    }
    @Override
    public void run() {
        while(true){
            try {
                Runnable task = taskQueue.take();
                System.out.println("Running task at " + System.currentTimeMillis());
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupt called in worker");
                break;
            }
        }
    }
}
