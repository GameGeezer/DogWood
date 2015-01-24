package framework.graphics.opengl.bufferObjects;

import framework.graphics.opengl.BufferedObjectUsage;
import org.lwjgl.opengl.GL15;

import java.nio.IntBuffer;

/**
 * A IBO is used to store data from a int buffer. This is most commonly used to store indices.
 *
 * @author William Gervasio
 */
public final class IBO {

    private final int handle;

    /**
     * @param buffer A buffer containing data that needs to be passed to the GPU
     * @param usage  An opengl hint describing how the data may be used
     */
    public IBO(IntBuffer buffer, BufferedObjectUsage usage) {

        handle = GL15.glGenBuffers();

        bind();

        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, usage.ID);

        unbind();
    }

    /**
     * Bind the buffer
     */
    public final void bind() {

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, handle);
    }

    /**
     * Unbind the buffer
     */
    public final void unbind() {

        //zero is reserved and acts as "no object"
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    /**
     * Releases the memory allocated by this buffer
     */
    public final void destroy() {

        GL15.glDeleteBuffers(handle);
    }
}