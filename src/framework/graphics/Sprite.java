package framework.graphics;

import framework.graphics.opengl.ShaderProgram;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William Gervasio
 */
public class Sprite {

    private ShaderProgram shader;
    private Mesh mesh;

    public Sprite(ShaderProgram shader) {

        this.shader = shader;

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

        int[] indices = {0, 1, 2, 0, 2, 3,};

        List<VertexAttribute> attributes = new ArrayList<VertexAttribute>();
        attributes.add(new VertexAttribute(vertices, 3));
        attributes.add(new VertexAttribute(colors, 4));
        mesh = new Mesh(indices, attributes);
    }

    public void draw() {
        shader.bind();
        mesh.draw();
        shader.unbind();
    }
}
