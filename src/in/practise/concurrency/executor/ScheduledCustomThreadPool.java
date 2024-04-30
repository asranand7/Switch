package in.practise.concurrency.executor;


import java.util.concurrent.ArrayBlockingQueue;

public class ScheduledCustomThreadPool {
    Thread[] threads;
    ArrayBlockingQueue<Runnable> taskQueue;
    public ScheduledCustomThreadPool(int threadCount){
        this.threads = new Thread[threadCount];
        taskQueue = new ArrayBlockingQueue<>(100);
        for(int i = 0;i < threadCount;i++){
            threads[i] = new Thread(new Worker(taskQueue), "Worker Thread " + i);
            threads[i].start();
        }
    }

    public void submit(Runnable task, long waitTimeInMillis) throws InterruptedException {
        try {
            taskQueue.put(() -> {
                try {
                    Thread.sleep(waitTimeInMillis);
                } catch (InterruptedException e) {
                    System.out.println("Interrut called in scheduled thread pool");
                    Thread.currentThread().interrupt();
                    System.out.println("Interrupt called");
                }
                task.run();
                System.out.println("Task ran at " + System.currentTimeMillis());
            });
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutDown(){
        for(int i = 0;i<threads.length;i++){
            threads[i].interrupt();
        }
    }


}
