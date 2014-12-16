package framework.graphics;

import framework.scene.Scene;
import org.lwjgl.opengl.GL11;

/**
 * @author william gervasio
 */

public class Renderer {

    public Renderer() {
        GL11.glFrontFace(GL11.GL_CCW);
        GL11.glCullFace(GL11.GL_BACK);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
//
// glEnable(GL_DEPTH_CLAMP);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public void clearScreen() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void setClearColor(Color color) {
        setClearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void setClearColor(float r, float g, float b, float a) {
        GL11.glClearColor(r, g, b, a);
    }
}
