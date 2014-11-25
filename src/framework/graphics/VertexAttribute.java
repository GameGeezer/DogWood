package framework.graphics;

/**
 * Created by Will on 2/12/14.
 */
public class VertexAttribute {
    private float[] data;
    private int elementsPerVertex;

    public VertexAttribute(float[] data, int elementsPerVertex) {
        this.data = data;
        this.elementsPerVertex = elementsPerVertex;
    }

    public float[] getData() {
        return data;
    }

    public int getElementsPerVertex() {
        return elementsPerVertex;
    }
}
