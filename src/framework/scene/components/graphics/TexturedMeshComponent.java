package framework.scene.components.graphics;

import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.Texture;
import framework.scene.components.RenderComponent;
import org.lwjgl.opengl.GL13;

/**
 * Created by Will on 12/8/2014.
 */
public class TexturedMeshComponent extends RenderComponent {

    private Mesh mesh;
    private Texture texture;

    public TexturedMeshComponent(Mesh mesh, Image image, ShaderProgram shader) {

        super(shader);

        this.mesh = mesh;
        this.texture = new Texture(image, GL13.GL_TEXTURE0);
    }

    @Override
    public void render(int delta) {

        getShader().bind();
        texture.bind();
        mesh.draw();
        texture.unbind();
        getShader().unbind();
    }
}
