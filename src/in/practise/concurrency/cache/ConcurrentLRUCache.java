package in.practise.concurrency.cache;

import in.practise.concurrency.readwritelock.CustomReadWriteLock;

import java.util.LinkedHashMap;

public class ConcurrentLRUCache<K,V> implements Cache<K,V>{
    int capacity;

    LinkedHashMap<K, V> linkedHashMap;

    CustomReadWriteLock readWriteLock = new CustomReadWriteLock();

    public ConcurrentLRUCache(int capacity){
        this.capacity = capacity;
        this.linkedHashMap = new LinkedHashMap<>();
    }

    @Override
    public V get(K key) throws InterruptedException, IllegalAccessException {
        readWriteLock.acquireReadLock();
        V value = linkedHashMap.get(key);
        if(value != null){
            linkedHashMap.remove(key);
            linkedHashMap.put(key, value);
        }
        readWriteLock.releaseReadLock();
        return value;
    }

    @Override
    public void put(K key, V value) throws InterruptedException, IllegalAccessException {
        readWriteLock.acquireWriteLock();
        if(linkedHashMap.containsKey(key)){
            linkedHashMap.remove(key);
            linkedHashMap.put(key, value);
        }else{
            if(linkedHashMap.size() == capacity){
                K firstKey = linkedHashMap.keySet().stream().findFirst().get();
                linkedHashMap.remove(firstKey);
            }
            linkedHashMap.put(key, value);
        }

        readWriteLock.releaseWriteLock();
    }
}
