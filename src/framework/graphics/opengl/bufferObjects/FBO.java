package framework.graphics.opengl.bufferObjects;

import org.lwjgl.opengl.GL30;

/**
 * Created by Will on 1/24/2015.
 */
public final class FBO {

    private final int handle;

    public FBO() {

        handle = GL30.glGenFramebuffers();
    }

    public final void bind() {

        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, handle);
    }

    public final void unbind() {

        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
    }


    public final void destroy() {

        GL30.glDeleteFramebuffers(handle);
    }
}
