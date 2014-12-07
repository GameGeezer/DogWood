package framework.util;

/**
 * Created by Will on 12/7/2014.
 */
public class FloatArrayList {
    private float[] data = new float[10];
    private int size;
    public FloatArrayList() {

    }

    public int size() {
        return size;
    }

    public void add(float value) {
        if(size == data.length - 1) {
            float[] newDataArray = new float[data.length * 2];
            System.arraycopy(data, 0, newDataArray, 0, data.length);
            data = newDataArray;
        }
        data[size] = value;
        ++size;
    }

    public void remove(int position) {
        int indexesUntilTheEnd = position - size;
        if(position >=0 && indexesUntilTheEnd > 0) {
            System.arraycopy(data, position + 1, data, position, indexesUntilTheEnd);
            --size;
        }
    }

    public float[] getAsArray(){
        float[] minimumArray = new float[size];
        System.arraycopy(data, 0, minimumArray, 0, size);
        return minimumArray;
    }
}
