package in.practise.concurrency.cache;

public class CacheMain {
    public static void main(String[] args) {
        Cache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "Anand");
        cache.put(2, "Sattu");
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.put(3, "Manya");
        System.out.println(cache.get(3));
        System.out.println(cache.get(1));
    }
}
