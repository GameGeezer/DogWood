package framework.graphics.opengl;

import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;

/**
 * A VBO is used to store data from a float buffer. This is most commonly used for vertex data: position, normal, color, ect.
 * @author William Gervasio
 */
public final class VBO {

    private final int handle;

    public VBO(FloatBuffer buffer, int usage) {
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
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    /**
     * Destroys the buffer
     */
    public final void destroy() {
        GL15.glDeleteBuffers(handle);
    }
}