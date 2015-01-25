package framework.graphics.vertices;

/**
 * A VertexAttribute that cannot be altered. Fastest if all the data for the attribute is known up front.
 *
 * @author William Gervasio
 */
public class StaticVertexAttribute implements VertexAttribute {

    private final float[] data;
    private final int elementsPerVertex;

    /**
     * @param data              An array of data for all the vertices.
     * @param elementsPerVertex The number of elements  in the array for each vertex.
     */
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
