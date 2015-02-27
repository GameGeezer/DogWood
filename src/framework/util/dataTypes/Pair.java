package framework.util.dataTypes;

/**
 * @author William Gervasio
 */
public class Pair<K, V> {

    public final K key;
    public final V value;

    public Pair(final K key, final V value) {

        this.key = key;
        this.value = value;
    }
}
