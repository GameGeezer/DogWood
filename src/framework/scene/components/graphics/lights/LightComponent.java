package framework.scene.components.graphics.lights;

import framework.graphics.Color;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.components.graphics.RenderComponent;

/**
 * Created by Will on 1/4/2015.
 */
public abstract class LightComponent extends RenderComponent {

    private Color color;
    private float intensity;

    public LightComponent(ShaderProgram shader, Color color, float intensity) {

        super(shader);

        this.color = color;
        this.intensity = intensity;
    }

    public void render(int delta) {

    }
}
