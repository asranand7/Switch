package in.practise.concurrency.executor;

public class ExecutorMain {
    public static void main(String[] args) throws InterruptedException {
        CustomThreadPool customThreadPool = new CustomThreadPool(2);
        Runnable task1 = () -> System.out.println("Executed by " + Thread.currentThread().getName() + " at " + System.currentTimeMillis());
        Runnable task2 = () -> System.out.println("Executed by " + Thread.currentThread().getName() + " at " + System.currentTimeMillis());
        Runnable task3 = () -> System.out.println("Executed by " + Thread.currentThread().getName() + " at " + System.currentTimeMillis());
        Runnable task4 = () -> System.out.println("Executed by " + Thread.currentThread().getName() + " at " + System.currentTimeMillis());
        Runnable task5 = () ->System.out.println("Executed by " + Thread.currentThread().getName() + " at " + System.currentTimeMillis());

        customThreadPool.submit(task1);
        customThreadPool.submit(task2);
        customThreadPool.submit(task3);
        customThreadPool.submit(task4);
        customThreadPool.submit(task5);
        customThreadPool.shutDown();
    }
}
