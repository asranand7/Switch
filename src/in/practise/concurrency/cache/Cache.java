package in.practise.concurrency.cache;

public interface Cache <K,V>{
    V get(K key) throws Exception;

    void put(K key, V value) throws Exception;
}