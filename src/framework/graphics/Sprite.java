package framework.graphics;

import framework.graphics.opengl.ShaderProgram;

/**
 * @author William Gervasio
 */
public class Sprite {

    private ShaderProgram shader;

    public Sprite(ShaderProgram shader) {
        this.shader = shader;
    }
}
