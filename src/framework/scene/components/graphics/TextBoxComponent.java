package framework.scene.components.graphics;

import framework.graphics.opengl.ShaderProgram;

/**
 * @author William Gervasio
 */
public class TextBoxComponent extends RenderComponent{

    private String text;

    public TextBoxComponent(ShaderProgram shader) {
        super(shader);
    }

    @Override
    public void render(int delta) {

    }
}
