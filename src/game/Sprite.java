package game;

import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.Texture;
import framework.graphics.vertices.IVertexAttribute;
import framework.graphics.vertices.StaticVertexAttribute;
import framework.graphics.vertices.VertexAttribute;
import framework.util.dataTypes.DatatypeUtil;
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

        List<IVertexAttribute> attributes = new ArrayList<>();
        attributes.add(new StaticVertexAttribute(vertices, 3));
        attributes.add(new StaticVertexAttribute(colors, 4));
        attributes.add(new StaticVertexAttribute(texCoords, 2));
        mesh = new Mesh(indices, attributes);
    }

    public void draw() {
        shader.bind();
        /*
        tempBuffer.reset();
        int projectionHandle = shader.getUniformLocation("u_projection");
        tempBuffer.put(camera.getProjection().data);
        GL20.glUniformMatrix4(projectionHandle, false, tempBuffer);

        tempBuffer.reset();
        tempBuffer.put(model.data);
        int modelHandle = shader.getUniformLocation("u_model");
        GL20.glUniformMatrix4(modelHandle, false, tempBuffer);
        */
        texture.bind();
        mesh.draw();
        texture.unbind();

        shader.unbind();
    }
}
