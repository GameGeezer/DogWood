package framework.graphics.vertices;

/**
 * Created by Will on 12/7/2014.
 */
public class StaticVertexAttribute implements IVertexAttribute {
    private final float[] data;
    private final int elementsPerVertex;

    public StaticVertexAttribute(float[] data, int elementsPerVertex) {
        this.data = data;
        this.elementsPerVertex = elementsPerVertex;
    }

    public final float[] getData() {
        return data;
    }

    public final int getElementsPerVertex() {
        return elementsPerVertex;
    }
}
