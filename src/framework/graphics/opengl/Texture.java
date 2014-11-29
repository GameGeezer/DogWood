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

    private int handle, textureUnit;

    public Texture(Image image, int textureUnit) {
        this(image.getBuffer(), image.getWidth(), image.getHeight(), textureUnit);
    }
    /**
     *
     * @param buffer
     * @param textureUnit i.e GL13.GL_TEXTURE0
     */
    public Texture(ByteBuffer buffer, int width, int height, int textureUnit) {
        handle = GL11.glGenTextures();
        this.textureUnit = textureUnit;
        GL13.glActiveTexture(textureUnit);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle);

        // All RGB bytes are aligned to each other and each component is 1 byte
        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

        // Upload the texture data and generate mip maps (for scaling)
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
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
