package framework.graphics.opengl;

import framework.util.dataTypes.DatatypeUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;

import java.nio.ByteBuffer;

/**
 * UBO(Uniform Buffer Object)
 *
 * @author William Gervasio
 */
public class UBO {

    private final int handle, binding;
    private final ByteBuffer buffer;

    public UBO(int binding, BufferedObjectUsage usage, int numberOfElements) {

        handle = GL15.glGenBuffers();
        this.binding = binding;

        buffer = BufferUtils.createByteBuffer(DatatypeUtil.FLOAT_SIZE_BYTES * numberOfElements);

        bind();

        GL15.glBufferData(GL31.GL_UNIFORM_BUFFER, buffer.capacity(), usage.ID);
    }

    public final void putFloat(int byteOffset, float data) {

        buffer.putFloat(byteOffset, data);
    }

    public final void putFloat(int byteOffset, int data) {

        buffer.putInt(byteOffset, data);
    }

    /**
     * Bind the buffer
     */
    public final void bind() {

        GL30.glBindBufferRange(GL31.GL_UNIFORM_BUFFER, binding, handle, 0, buffer.capacity());

    }

    public final void unbind() {

        GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0);
    }

    /**
     * Must be called after the buffer is changed
     */
    public final void refresh() {

        GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, handle);
        GL15.glBufferSubData(GL31.GL_UNIFORM_BUFFER, 0, buffer);
    }
}
