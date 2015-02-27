package framework.util.dataTypes;

/**
 * @author William Gervasio
 */
public class Pair<K, V> {

    public K key;
    public V value;

    public Pair(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    public Pair(final Pair<K, V> pair) {
        this(pair.key, pair.value);
    }

}
