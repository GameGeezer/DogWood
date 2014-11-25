package framework.graphics.opengl;

import org.lwjgl.opengl.GL15;

import java.nio.IntBuffer;

/**
 * A VBO is used to store data from a int buffer. This is most commonly used to store indices.
 * @author William Gervasio
 */
public class IBO {

    private final int handle;

    public IBO(IntBuffer buffer, int usage) {
        handle = GL15.glGenBuffers();

        bind();
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, usage);
        unbind();
    }

    /**
     * Bind the buffer
     */
    public void bind() {
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, handle);
    }

    /**
     * Unbind the buffer
     */
    public void unbind() {
        //zero is reserved and acts as "no object"
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    /**
     * Delete the buffer
     */
    public void destroy() {
        GL15.glDeleteBuffers(handle);
    }
}