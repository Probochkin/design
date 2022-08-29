package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference temp = new SoftReference<>(value);
        if (!cache.containsKey(key)) {
            cache.put(key, temp);
        }
    }

    public V get(K key) {
        V strong = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (strong == null) {
            strong = this.load(key);
            put(key, strong);
        }
        return strong;
    }

    protected abstract V load(K key);

}
