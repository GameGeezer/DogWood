package framework.graphics.opengl.bufferObjects;

import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

/**
 * Render buffer objects are buffers that can be attached toa Frame Buffer Object(FBO). They hold data
 * that can be drawn to the screen.
 *
 * @author William Gervasio
 */
@SuppressWarnings("SameParameterValue")
public final class RBO {

    private final int handle;
    private final AttachmentType frameBufferAttachmentType;

    public RBO(final int width, final int height, AttachmentType frameBufferAttachmentType) {

        this.frameBufferAttachmentType = frameBufferAttachmentType;

        handle = GL30.glGenRenderbuffers();

        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, handle);
        // Set the format and dimensions of the buffer
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT24, width, height);
        // Attach this render buffer to a currently bound frame buffer(FBO)
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, frameBufferAttachmentType.ID, GL30.GL_RENDERBUFFER, handle); // TODO might be able to remove?
        // Unbind this buffer
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, 0);
    }

    public final void bind() {
        // Zero is reserved and unbinds the current render buffer
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, handle);
        // Attach this render buffer to a currently bound frame buffer(FBO)
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, frameBufferAttachmentType.ID, GL30.GL_RENDERBUFFER, handle);
    }

    public final void unbind() {
        // Zero is reserved and unbinds the current render buffer
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, 0);
    }
}
