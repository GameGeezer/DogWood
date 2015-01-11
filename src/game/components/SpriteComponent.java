package game.components;

import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.Texture;
import framework.graphics.uniform.MatrixUniform;
import framework.graphics.vertices.IVertexAttribute;
import framework.graphics.vertices.StaticVertexAttribute;
import framework.scene.components.graphics.RenderComponent;
import framework.util.math.Matrix4;
import org.lwjgl.opengl.GL13;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 12/24/2014.
 */
public class SpriteComponent extends RenderComponent {

    private Texture texture;
    private Mesh mesh;

    private Matrix4 textureMatrix = new Matrix4();
    private MatrixUniform textureMatrixUniform = new MatrixUniform("u_textureMatrix", MatrixUniform.MatrixUniformType.MATRIX4);

    public SpriteComponent(Image image, ShaderProgram shader, int cellsWide, int cellsHigh) {

        super(shader);

        texture = new Texture(image, GL13.GL_TEXTURE0);

        mesh = createMesh(texture.getWidth(), texture.getHeight(), cellsWide, cellsHigh);

        textureMatrixUniform.addListener(shader);

        textureMatrix.data[Matrix4.M03] = 0f;
        textureMatrix.data[Matrix4.M13] = 0f;

        updateTextureMatrix();
    }

    @Override
    public void render(int delta) {
        getShader().bind();
        texture.bind();
        mesh.draw();
        texture.unbind();
        getShader().unbind();
    }


    private void updateTextureMatrix() {

        textureMatrixUniform.setUniformData(textureMatrix.data);
    }

    private Mesh createMesh(int width, int height, int cellsWide, int cellsHigh) {

        float widthHeightRatio = (float) width / (float) height;
        float xRatio = widthHeightRatio / 2f;
        float yRatio = 1 - xRatio;

        float[] vertices = {
                -xRatio, yRatio, 0f,
                -xRatio, -yRatio, 0f,
                xRatio, -yRatio, 0f,
                xRatio, yRatio, 0f,
        };

        float texcoordX = (1.0f / (float) cellsWide);
        float texcoordY = (1.0f / (float) cellsHigh);

        float[] texCoords = {
                0f, 0f,
                0f, texcoordY,
                texcoordX, texcoordY,
                texcoordX, 0f,
        };

        int[] indices = {0, 1, 2, 0, 2, 3,};

        List<IVertexAttribute> attributes = new ArrayList<>();
        attributes.add(new StaticVertexAttribute(vertices, 3));
        attributes.add(new StaticVertexAttribute(texCoords, 2));

        return new Mesh(indices, attributes);
    }
}
