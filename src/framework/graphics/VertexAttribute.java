package framework.graphics;

/**
 * @author William Gervasio
 */
public final class VertexAttribute {
    private final float[] data;
    private final int elementsPerVertex;

    public VertexAttribute(float[] data, int elementsPerVertex) {
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
