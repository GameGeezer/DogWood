package framework.scene.components.graphics;

import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;

/**
 * @author William Gervasio
 */
public class MeshComponent extends RenderComponent {

    private Mesh mesh;

    public MeshComponent(final Mesh mesh, final ShaderProgram shader) {

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
