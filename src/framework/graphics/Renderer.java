package framework.graphics;

import framework.scene.Scene;
import org.lwjgl.opengl.GL11;

/**
 * @author william gervasio
 */

public abstract class Renderer {

    public Renderer() {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void setClearColor(Color color) {
        setClearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void setClearColor(float r, float g, float b, float a) {
        GL11.glClearColor(r, g, b, a);
    }

    public void render(Scene scene, Camera camera, int delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

    }
}
