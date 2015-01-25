package framework.graphics.vertices;

/**
 * A VertexAttribute is a wrapper for data being used to create a mesh.
 *
 * @author William Gervasio
 */
public interface VertexAttribute {

    /**
     * The data as a float array
     *
     * @return
     */
    public abstract float[] getData();

    /**
     * How many indexes in the data make up one vertex
     *
     * @return
     */
    public abstract int getElementsPerVertex();
}
