package framework.graphics.vertices;

/**
 * An IVertexAttribute that cannot be altered.
 */
public class StaticVertexAttribute implements IVertexAttribute {
    private final float[] data;
    private final int elementsPerVertex;

    public StaticVertexAttribute(float[] data, int elementsPerVertex) {
        this.data = data;
        this.elementsPerVertex = elementsPerVertex;
    }

    @Override
    public final float[] getData() {
        return data;
    }

    @Override
    public final int getElementsPerVertex() {
        return elementsPerVertex;
    }
}
