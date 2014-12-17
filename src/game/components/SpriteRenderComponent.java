package game.components;

import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.Texture;
import framework.graphics.vertices.IVertexAttribute;
import framework.graphics.vertices.StaticVertexAttribute;
import framework.scene.Entity;
import framework.scene.components.IDynamicComponent;
import framework.scene.components.IRenderComponent;
import org.lwjgl.opengl.GL13;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William Gervasio
 */
public final class SpriteRenderComponent extends Entity.EntityComponent implements IRenderComponent {

    private ShaderProgram shader;
    private Texture texture;
    private Mesh mesh;
    private Image image;

    public SpriteRenderComponent(Image image, ShaderProgram shader) {

        this.shader = shader;
        this.image = image;
        texture = new Texture(image, GL13.GL_TEXTURE0);

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
        };


        float[] colors = {
                1f, 0f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 0f, 1f, 1f,
                1f, 1f, 1f, 1f,
        };

        float[] texCoords = {
                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f,
        };

        int[] indices = {0, 1, 2, 0, 2, 3,};

        List<IVertexAttribute> attributes = new ArrayList<>();
        attributes.add(new StaticVertexAttribute(vertices, 3));
        attributes.add(new StaticVertexAttribute(colors, 4));
        attributes.add(new StaticVertexAttribute(texCoords, 2));
        mesh = new Mesh(indices, attributes);
    }

    @Override
    public void render(int delta) {
        shader.bind();
        texture.bind();
        mesh.draw();
        texture.unbind();

        shader.unbind();
    }

    @Override
    public ShaderProgram getShader() {
        return shader;
    }

}
