public interface SimpleHashMap<K, V> extends Iterable<Pair<K, V>> {
    boolean insert(K key, V value);
    V get(K key);
    boolean delete(K key);
}
