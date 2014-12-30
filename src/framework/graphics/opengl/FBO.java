package framework.graphics.opengl;

import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;

/**
 * A FBO is used to store data from a float buffer. This is most commonly used for vertex data: position, normal, color, ect.
 *
 * @author William Gervasio
 */
public final class FBO {

    private final int handle;

    /**
     *
     * @param buffer A buffer containing data that needs to be passed to the GPU
     * @param usage An opengl hint describing how the data may be used
     */
    public FBO(FloatBuffer buffer, int usage) {

        handle = GL15.glGenBuffers();

        bind();

        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, usage);

        unbind();
    }

    /**
     * Binds the buffer
     */
    public final void bind() {

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, handle);
    }

    /**
     * Unbinds the buffer
     */
    public final void unbind() {

        //zero is reserved and acts as "no object"
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    /**
     * Releases the memory allocated by this buffer
     */
    public final void destroy() {

        GL15.glDeleteBuffers(handle);
    }
}