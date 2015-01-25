package framework.graphics.opengl;

import framework.graphics.Image;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;

/**
 * @author William Gervasio
 */
public class Texture {

    private final int handle, textureUnit, width, height;
    private OGLColorType colorType;

    /**
     * @param buffer
     * @param textureUnit i.e GL13.GL_TEXTURE0
     */
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
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, colorType.ID, width, height, 0, colorType.RGBA.ID, GL11.GL_UNSIGNED_BYTE, buffer);
        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
    }

    public Texture(final int width, final int height, final int textureUnit, final OGLColorType colorType) {

        this(width, height, textureUnit, colorType, null);
    }

    public Texture(final Image image, final int textureUnit) {

        this(image.getWidth(), image.getHeight(), textureUnit, OGLColorType.RGBA8, image.getBuffer());
    }

    public void bind() {

        GL13.glActiveTexture(textureUnit);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle);
    }

    public void unbind()
    {

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public void destroy() {

        GL11.glDeleteTextures(handle);
    }

    public int getWidth() {

        return width;
    }

    public int getHeight() {

        return height;
    }
}
