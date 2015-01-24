package framework.graphics;

import framework.graphics.opengl.OGLColorType;
import framework.graphics.opengl.Texture;
import org.lwjgl.opengl.GL11;

/**
 * Created by Will on 1/23/2015.
 */
public class DeferredRenderer {

    public static final int POSITION_BUFFER_BINDING = 0;
    public static final int NORMAL_BUFFER_BINDING = 1;
    public static final int DIFFUSE_BUFFER_BINDING = 2;

    private final Texture positionBuffer;
    private final Texture normalBuffer;
    private final Texture diffuseBuffer;

    public DeferredRenderer(final int width, final int height) {

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE); // Enable later

        positionBuffer = new Texture(width, height, POSITION_BUFFER_BINDING, OGLColorType.RGBA16F);
        normalBuffer = new Texture(width, height, NORMAL_BUFFER_BINDING, OGLColorType.RGBA16F);
        diffuseBuffer = new Texture(width, height, DIFFUSE_BUFFER_BINDING, OGLColorType.RGBA8);
    }

    public void destroy() {

        positionBuffer.destroy();
        normalBuffer.destroy();
        diffuseBuffer.destroy();
    }
}
