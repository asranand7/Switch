package in.practise.concurrency.executor;


import java.util.concurrent.ArrayBlockingQueue;

public class CustomThreadPool {
    Thread[] threads;
    ArrayBlockingQueue<Runnable> taskQueue;
    public CustomThreadPool(int threadCount){
        this.threads = new Thread[threadCount];
        taskQueue = new ArrayBlockingQueue<>(100);
        for(int i = 0;i < threadCount;i++){
            threads[i] = new Thread(new Worker(taskQueue), "Worker Thread " + i);
            threads[i].start();
        }
    }

    public void submit(Runnable task) throws InterruptedException {
        try {
            taskQueue.put(task);
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
