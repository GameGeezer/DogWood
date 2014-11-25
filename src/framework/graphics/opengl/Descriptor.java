package framework.graphics.opengl;

/**
 * Descriptors are used to describe generic the data for generic attribute pointers.
 * @author William Gervasio
 */
public class Descriptor {

    private final int size;
    private final int type;
    private final boolean normalized;
    private final int stride;
    private final int pointer;

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
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * The data type. e.g. GL_FLOAT
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     * Whether to normalize the data
     * @return
     */
    public boolean isNormalized() {
        return normalized;
    }

    /**
     * How many bytes total to store all the vertices
     * @return
     */
    public int getStride() {
        return stride;
    }

    /**
     * The offset in bytes of the first index
     * @return
     */
    public int getPointer() {
        return pointer;
    }
}