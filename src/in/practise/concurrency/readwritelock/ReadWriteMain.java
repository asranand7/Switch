package in.practise.concurrency.readwritelock;

public class ReadWriteMain {
    public static void main(String[] args) throws InterruptedException {

        final CustomReadWriteLock readWriteLock = new CustomReadWriteLock();

        Thread writer1 = new Thread(() -> {
            try {

                System.out.println("Attempting to acquire write lock in thread 1: " + System.currentTimeMillis());
                readWriteLock.acquireWriteLock();
                System.out.println("write lock acquired thread 1: " + +System.currentTimeMillis());

                    Thread.sleep(12000);

            } catch (InterruptedException ie) {

            }
        });

        Thread writer2 = new Thread(() -> {
            try {

                System.out.println("Attempting to acquire write lock in thread 2: " + System.currentTimeMillis());
                readWriteLock.acquireWriteLock();
                System.out.println("write lock acquired thread 2: " + System.currentTimeMillis());

            } catch (InterruptedException ie) {

            }
        });

        Thread writer3 = new Thread(() -> {
            try {

                System.out.println("Release write lock in thread 3: " + System.currentTimeMillis());
                readWriteLock.releaseWriteLock();
                System.out.println("Released write lock in thread 3: " + System.currentTimeMillis());

            } catch (InterruptedException | IllegalAccessException ie) {

            }
        });

        Thread tReader1 = new Thread(() -> {
            try {

                readWriteLock.acquireReadLock();
                System.out.println("Read lock acquired: " + System.currentTimeMillis());

            } catch (InterruptedException ie) {
            }
        });

        Thread tReader2 = new Thread(() -> {

            System.out.println("Read lock about to release: " + System.currentTimeMillis());
            try {
                readWriteLock.releaseReadLock();
                System.out.println("Read lock released: " + System.currentTimeMillis());
            } catch (InterruptedException | IllegalAccessException e) {
            }
        });

        tReader1.start();
        writer1.start();
        Thread.sleep(3000);
        tReader2.start();
        Thread.sleep(1000);
        writer3.start();
        writer2.start();
        tReader1.join();
        tReader2.join();
        writer2.join();
    }
}
