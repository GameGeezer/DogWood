package game.components;

import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.Texture;
import framework.scene.components.RenderComponent;

/**
 * Created by Will on 12/25/2014.
 */
public class TileMapComponent extends RenderComponent {

    private Texture texture;
    private Mesh mesh;

    public TileMapComponent(ShaderProgram shader) {
        super(shader);

    }

    public void render(int delta) {
        getShader().bind();
        texture.bind();
        mesh.draw();
        texture.unbind();
        getShader().unbind();
    }
}
