package framework.graphics.opengl.bufferObjects;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL15.*;

import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL30.glBindBufferRange;
import static org.lwjgl.opengl.GL31.GL_UNIFORM_BUFFER;

public class UBO {

    private final int handle, binding;
    private ByteBuffer buffer;

    /**
     *
     * @param usage streamdraw
     */
    public UBO(final int binding, final ByteBuffer buffer, final BufferedObjectUsage usage) {

        this.binding = binding;
        this.buffer = buffer;
        handle = glGenBuffers();

        glBindBuffer(GL_UNIFORM_BUFFER, handle);
        glBufferData(GL_UNIFORM_BUFFER, buffer.capacity(), usage.ID);
        glBindBuffer(GL_UNIFORM_BUFFER, 0);
    }

    public void bind() {

        glBindBuffer(GL_UNIFORM_BUFFER, handle);
        glBindBufferRange(GL_UNIFORM_BUFFER, binding, handle, 0, buffer.capacity());
    }

    public void unbind() {

        glBindBuffer(GL_UNIFORM_BUFFER, 0);
    }

    public void destroy() {

        glDeleteBuffers(handle);
    }
}
