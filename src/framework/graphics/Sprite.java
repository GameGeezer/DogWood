package framework.graphics;

import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.Texture;
import framework.util.DatatypeUtil;
import framework.util.math.Matrix4;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author William Gervasio
 */
public class Sprite {

    private ShaderProgram shader;
    private Image image;
    private Texture texture;
    private Mesh mesh;
    private FloatBuffer tempBuffer = FloatBuffer.allocate(DatatypeUtil.FLOAT_SIZE_BYTES * 16);

    public Sprite(Image image, ShaderProgram shader) {

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

        List<VertexAttribute> attributes = new ArrayList<VertexAttribute>();
        attributes.add(new VertexAttribute(vertices, 3));
        attributes.add(new VertexAttribute(colors, 4));
        attributes.add(new VertexAttribute(texCoords, 2));
        mesh = new Mesh(indices, attributes);
    }

    public void draw(Camera camera, Matrix4 model) {
        shader.bind();

        tempBuffer.reset();
        int projectionHandle = shader.getUniformLocation("u_projection");
        tempBuffer.put(camera.getProjection().data);
        GL20.glUniformMatrix4(projectionHandle, false, tempBuffer);

        tempBuffer.reset();
        tempBuffer.put(model.data);
        int modelHandle = shader.getUniformLocation("u_model");
        GL20.glUniformMatrix4(modelHandle, false, tempBuffer);

        texture.bind();
        mesh.draw();
        texture.unbind();

        shader.unbind();
    }
}
