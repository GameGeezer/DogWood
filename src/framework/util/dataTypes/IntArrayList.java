package framework.util.dataTypes;

/**
 * @author William Gervasio
 */
public class IntArrayList {

    private static final int DEFAULT_INITAL_CAPACITY = 10;
    private static final float DEFAULT_GROWTH_FACTOR = 2.0f;

    private int[] data;
    private int size;
    private final float growth;

    public IntArrayList() {
        this(DEFAULT_INITAL_CAPACITY, DEFAULT_GROWTH_FACTOR);
    }

    public IntArrayList(final int capacity) {
        this(capacity, DEFAULT_GROWTH_FACTOR);
    }

    public IntArrayList(final int capacity, final float growthFactor) {
        data = new int[capacity];
        size = 0;
        growth = growthFactor;
    }

    public int size() {
        return size;
    }

    public void add(final int value) {
        if (size == data.length - 1) {
            final int[] newDataArray = new int[(int) (data.length * growth)];
            System.arraycopy(data, 0, newDataArray, 0, data.length);
            data = newDataArray;
        }
        data[size] = value;
        ++size;
    }

    public void remove(final int position) {
        final int indexesUntilTheEnd = position - size;
        if (position >= 0 && indexesUntilTheEnd > 0) {
            System.arraycopy(data, position + 1, data, position, indexesUntilTheEnd);
            --size;
        }
    }

    public void clear() {
        size = 0;
    }

    public int[] toArray() {
        final int[] minimumArray = new int[size];
        System.arraycopy(data, 0, minimumArray, 0, size);
        return minimumArray;
    }

}
