package framework.scene.components.graphics;

import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;

/**
 * Created by Will on 1/4/2015.
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
