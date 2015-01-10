package framework.graphics.opengl;

/**
 * Descriptors are used to describe the data used to define generic attribute pointers.
 *
 * @author William Gervasio
 */
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
     * @param stride
     * @param pointer
     */
    public Descriptor(final int size, final int type, final boolean normalized,
                      final int stride, final int pointer) {

        this.size = size;
        this.type = type;
        this.normalized = normalized;
        this.stride = stride;
        this.pointer = pointer;
    }

    /**
     * The element count per vertex. e.g. 3(x, y, z) for position
     *
     * @return
     */
    public final int getSize() {
        return size;
    }

    /**
     * The data type. e.g. GL_FLOAT
     *
     * @return
     */
    public final int getType() {
        return type;
    }

    /**
     * Whether to normalize the data or not
     *
     * @return
     */
    public final boolean isNormalized() {
        return normalized;
    }

    /**
     * How many bytes total to store all the vertices
     *
     * @return
     */
    public final int getStride() {
        return stride;
    }

    /**
     * The offset in bytes of the first index
     *
     * @return
     */
    public final int getPointer() {
        return pointer;
    }
}