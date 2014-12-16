package game.components;

import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.Entity.EntityComponent;
import framework.scene.components.IDynamicComponent;
import framework.scene.components.IRenderComponent;

/**
 * Created by Will on 12/8/2014.
 */
public class MeshComponent extends EntityComponent implements IDynamicComponent, IRenderComponent {

    private Mesh mesh;
    private ShaderProgram shader;

    public MeshComponent(Mesh mesh, ShaderProgram shader) {

        this.mesh = mesh;
        this.shader = shader;

    }

    @Override
    public void update(int delta) {
        shader.bind();
        mesh.draw();
        shader.unbind();
    }

    @Override
    public ShaderProgram getShader() {
        return shader;
    }
}
