package in.practise.concurrency.mutex;

public class CustomMutex {
    private boolean locked;

    public synchronized void lock() throws InterruptedException {
        while(locked){
            wait();
        }
        locked = true;
    }

    public synchronized void unlock(){
        locked = false;
        notifyAll();
    }
}
