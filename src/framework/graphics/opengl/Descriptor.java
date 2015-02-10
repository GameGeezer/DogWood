package framework.graphics.opengl;

/**
 * Descriptors are used to describe the data used to define generic attribute pointers.
 *
 * @author William Gervasio
 */
@SuppressWarnings("SameParameterValue")
public final class Descriptor {

    private final int size;
    private final int type;
    private final boolean normalized;
    private final int stride;
    private final int pointer;

    /**
     * @param size       The number of elements per vertex
     * @param type       The data type. "GL_FLOAT" for example
     * @param normalized Whether the data should be normalized or not
     * @param stride     The size in bytes needed to store all the vertices
     * @param pointer    The offset in bytes of the first index
     */
    public Descriptor(final int size, final int type, final boolean normalized, final int stride, final int pointer) {

        this.size = size;
        this.type = type;
        this.normalized = normalized;
        this.stride = stride;
        this.pointer = pointer;
    }

    /**
     * Get the element count per vertex. e.g. 3(x, y, z) for position
     *
     * @return  the size
     */
    public final int getSize() {

        return size;
    }

    /**
     * Get the data type. e.g. GL_FLOAT
     *
     * @return The type
     */
    public final int getType() {

        return type;
    }

    /**
     * Get whether to normalize the data or not
     *
     * @return whether to normalize or not
     */
    public final boolean isNormalized() {

        return normalized;
    }

    /**
     * Get how many bytes in total are required to store all the vertices
     *
     * @return The stride
     */
    public final int getStride() {

        return stride;
    }

    /**
     * The offset in bytes of the first index
     *
     * @return The pointer to the first index
     */
    public final int getPointer() {

        return pointer;
    }
}