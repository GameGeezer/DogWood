package framework.graphics.opengl;

import framework.graphics.Image;
import framework.graphics.opengl.bufferObjects.AttachmentType;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;

/**
 * @author William Gervasio
 */
@SuppressWarnings("UnusedDeclaration")
public class Texture {

    private final int handle;

    public final OGLColorType colorType;
    public final int textureUnit, width, height;

    public Texture(final int width, final int height, final int textureUnit, final OGLColorType colorType, final ByteBuffer buffer) {

        this.handle = GL11.glGenTextures();
        this.textureUnit = textureUnit;
        this.width = width;
        this.height = height;
        this.colorType = colorType;

        GL13.glActiveTexture(textureUnit);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle);

        // All RGB bytes are aligned to each other and each component is 1 byte
        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

        // Upload the texture data and generate mip maps (for scaling)
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, colorType.ID, width, height, 0, colorType.ID, GL11.GL_UNSIGNED_BYTE, buffer);
        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
    }

    public Texture(final int width, final int height, final int textureUnit, final OGLColorType colorType) {

        this(width, height, textureUnit, colorType, null);
    }

    public Texture(final Image image, final int textureUnit) {

        this(image.getWidth(), image.getHeight(), textureUnit, OGLColorType.RGBA, image.getBuffer());
    }

    public final void attachToFBO(final AttachmentType attachment) {
        // Add this texture as a buffer to the Frame Buffer Objects
        GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, attachment.ID, GL11.GL_TEXTURE_2D, handle, 0);
    }

    public void bind() {

        GL13.glActiveTexture(textureUnit);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle);
    }

    public void unbind() {

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public void destroy() {

        GL11.glDeleteTextures(handle);
    }
}
