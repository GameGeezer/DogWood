package game.components;

import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity.EntityComponent;
import framework.scene.components.RenderComponent;

/**
 * Created by Will on 12/8/2014.
 */
public class MeshComponent extends RenderComponent {

    private Mesh mesh;
    private ShaderProgram shader;

    public MeshComponent(Mesh mesh, ShaderProgram shader) {

        this.mesh = mesh;
        this.shader = shader;

    }

    @Override
    public void render(int delta) {
        shader.bind();
        mesh.draw();
        shader.unbind();
    }

    @Override
    public ShaderProgram getShader() {
        return shader;
    }
}
