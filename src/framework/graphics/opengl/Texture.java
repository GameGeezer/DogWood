package framework.graphics.opengl;

import org.lwjgl.opengl.GL11;

/**
 * @author William Gervasio
 */
public class Texture {

    private int handle;

    public Texture() {
        handle = GL11.glGenTextures();
    }

    public void bind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle);
    }

    public void unbind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
}
