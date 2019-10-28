package cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Cache<K, V> {
    private final Map<K, SoftReference<V>> map;
    private Function<K, V> loadBehaviour;

    public Cache(int size) {
        map = new HashMap<>((int)(size / 0.75) + 1);
    }

    public void setLoadBehaviour(Function<K, V> loadBehaviour) {
        this.loadBehaviour = loadBehaviour;
    }

    public V getValueFromCacheByKey(K key) {
        if (map.get(key) == null) {
            map.put(key, new SoftReference<>(loadBehaviour.apply(key)));
        } else if (map.get(key).get() == null) {
            map.replace(key, new SoftReference<>(loadBehaviour.apply(key)));
        }
        return map.get(key).get();
    }
}
