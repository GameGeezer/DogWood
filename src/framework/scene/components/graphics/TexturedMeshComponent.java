package framework.scene.components.graphics;

import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.Texture;
import org.lwjgl.opengl.GL13;

/**
 * Created by Will on 12/8/2014.
 */
public class TexturedMeshComponent extends RenderComponent {

    private final Mesh mesh;
    private final Texture texture;

    public TexturedMeshComponent(final Mesh mesh, final Image image, final ShaderProgram shader) {

        super(shader);

        this.mesh = mesh;
        this.texture = new Texture(image, GL13.GL_TEXTURE0);
    }

    @Override
    public void render(final int delta) {

        getShader().bind();
        texture.bind();
        mesh.draw();
        texture.unbind();
        getShader().unbind();
    }
}
