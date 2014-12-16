package game.components;

import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.components.DynamicEntityComponent;
import framework.scene.components.EntityComponent;

import java.util.List;

/**
 * Created by Will on 12/8/2014.
 */
public class MeshComponent extends DynamicEntityComponent {

    private Mesh mesh;
    private ShaderProgram shader;

    public MeshComponent(Entity parent, Mesh mesh, ShaderProgram shader) {
        super(parent);

        this.mesh = mesh;
        this.shader = shader;

    }

    @Override
    public void update(int delta) {
        shader.bind();
        mesh.draw();
        shader.unbind();
    }

    public ShaderProgram getShader() {
        return shader;
    }
}
