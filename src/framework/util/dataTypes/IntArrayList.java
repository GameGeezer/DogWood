package framework.util.dataTypes;

/**
 * Created by Will on 12/7/2014.
 */
public class IntArrayList {

    private int[] data = new int[10];
    private int size;

    public IntArrayList() {

    }

    public int size() {
        return size;
    }

    public void add(int value) {

        if (size == data.length - 1) {

            int[] newDataArray = new int[data.length * 2];

            System.arraycopy(data, 0, newDataArray, 0, data.length);

            data = newDataArray;
        }

        data[size] = value;

        ++size;
    }

    public void remove(int position) {

        int indexesUntilTheEnd = position - size;

        if (position >= 0 && indexesUntilTheEnd > 0) {

            System.arraycopy(data, position + 1, data, position, indexesUntilTheEnd);

            --size;
        }
    }

    public int[] getAsArray() {
        int[] minimumArray = new int[size];
        System.arraycopy(data, 0, minimumArray, 0, size);
        return minimumArray;
    }
}
