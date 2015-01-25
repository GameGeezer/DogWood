package framework.graphics.opengl.bufferObjects;

import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

/**
 * Render buffer object
 */
public final class RBO {

    enum RenderBufferStorage {

    }

    private final int handle, width, height;

    public RBO(final int width, final int height) {

        this.width = width;
        this.height = height;

        handle = GL30.glGenRenderbuffers();

        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, handle);
        // Set the format and dimensions of the buffer
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT24, width, height);
        //
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL30.GL_RENDERBUFFER, handle);
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, 0);
    }

    public final void bind() {
        // Zero is reserved and unbinds the current render buffer
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, handle);
    }

    public final void unbind() {
        // Zero is reserved and unbinds the current render buffer
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, 0);
    }
}
