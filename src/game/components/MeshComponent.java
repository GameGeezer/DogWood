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

    public MeshComponent(Mesh mesh, ShaderProgram shader) {

        super(shader);

        this.mesh = mesh;
    }

    @Override
    public void render(int delta) {
        getShader().bind();
        mesh.draw();
        getShader().unbind();
    }
}
