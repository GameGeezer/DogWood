package game.components;

import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.TextureAtlas;
import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.Texture;
import framework.graphics.uniform.FloatVectorUniform;
import framework.graphics.uniform.MatrixUniform;
import framework.graphics.uniform.VectorUniform;
import framework.graphics.vertices.IVertexAttribute;
import framework.graphics.vertices.StaticVertexAttribute;
import framework.scene.components.RenderComponent;
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

    private int flippedX, flippedY;

    private Matrix4 textureMatrix = new Matrix4();
    private MatrixUniform textureMatrixUniform = new MatrixUniform("u_textureMatrix", MatrixUniform.MatrixUniformType.MATRIX4);
    private FloatVectorUniform flipTexCoordsUniform = new FloatVectorUniform("u_flipTextureCoordinates", VectorUniform.VectorUniformType.VECTOR2);

    public SpriteComponent(Image image, ShaderProgram shader, int cellsWide, int cellsHigh) {

        super(shader);

        texture = new Texture(image, GL13.GL_TEXTURE0);

        float widthHeightRatio = (float)image.getWidth() / (float)image.getHeight();
        float xRatio = widthHeightRatio / 2f;
        float yRatio = 1 - xRatio;

        float[] vertices = {
                -xRatio, yRatio, 0f,
                -xRatio, -yRatio, 0f,
                xRatio, -yRatio, 0f,
                xRatio, yRatio, 0f,
        };

        float texcoordX = (1.0f /  (float) cellsWide) ;
        float texcoordY = (1.0f /  (float) cellsHigh) ;

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

        mesh = new Mesh(indices, attributes);

        textureMatrixUniform.addListener(shader);
        flipTexCoordsUniform.addListener(shader);

        textureMatrix.data[Matrix4.M03] = 0.5f;
        textureMatrix.data[Matrix4.M13] = 0.5f;

        updateTextureMatrix();
        setFlipped(false, false);
    }

    @Override
    public void render(int delta) {
        getShader().bind();
        texture.bind();
        mesh.draw();
        texture.unbind();
        getShader().unbind();
    }

    public void setFlippedX(boolean flipped) {
        flippedX = flipped ? -1 : 1;
        updateFlippedTexcoordBuffer();
    }

    public void setFlippedY(boolean flipped) {
        flippedY = flipped ? -1 : 1;
        updateFlippedTexcoordBuffer();
    }

    public void setFlipped(boolean isFlippedX, boolean isFlippedY) {
        flippedX = isFlippedX ? -1 : 1;
        flippedY = isFlippedY ? -1 : 1;
        updateFlippedTexcoordBuffer();
    }

    private void updateFlippedTexcoordBuffer() {
        flipTexCoordsUniform.setUniformData(flippedX, flippedY);
    }

    private void updateTextureMatrix() {

        textureMatrixUniform.setUniformData(textureMatrix.data);
    }
}
