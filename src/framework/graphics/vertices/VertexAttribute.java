package framework.graphics.vertices;

/**
 * A VertexAttribute is a wrapper for data being used to create a mesh.
 *
 * @author William Gervasio
 */
public interface VertexAttribute {

    /**
     * Get the vertex attribute data
     *
     * @return The vertex data as a float array
     */
    public abstract float[] getData();

    /**
     * How many indexes in the data make up one vertex
     *
     * @return The number of elements in the array that describe one vertex
     */
    public abstract int getElementsPerVertex();
}
