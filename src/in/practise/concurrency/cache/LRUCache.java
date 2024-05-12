package in.practise.concurrency.cache;

import java.util.LinkedHashMap;

public class LRUCache<K,V> implements Cache<K,V>{
    int capacity;

    LinkedHashMap<K, V> linkedHashMap;

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.linkedHashMap = new LinkedHashMap<>();
    }

    @Override
    public V get(K key) {
        V value = linkedHashMap.get(key);
        if(value != null){
            linkedHashMap.remove(key);
            linkedHashMap.put(key, value);
        }
        return value;
    }

    @Override
    public void put(K key, V value) {
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
    }
}
