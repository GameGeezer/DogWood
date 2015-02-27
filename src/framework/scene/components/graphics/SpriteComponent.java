package framework.scene.components.graphics;

import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.Texture;
import framework.graphics.opengl.uniform.FloatVectorUniform;
import framework.graphics.opengl.uniform.VectorUniform;
import framework.graphics.vertices.StaticVertexAttribute;
import framework.graphics.vertices.VertexAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William Gervasio
 */
public class SpriteComponent extends RenderComponent {

    private final Texture texture;
    private final Mesh mesh;
    private final int cellsWide, cellsHigh;
    private final float texcoordX, texcoordY;

    private final FloatVectorUniform texCoordOffset = new FloatVectorUniform("u_textureCoordinateOffset", VectorUniform.VectorUniformType.VECTOR2);

    public SpriteComponent(final Image image, ShaderProgram shader, int cellsWide, int cellsHigh) {

        super(shader);

        texcoordX = (1.0f / (float) cellsWide);
        texcoordY = (1.0f / (float) cellsHigh);
        this.cellsWide = cellsWide;
        this.cellsHigh = cellsHigh;

        texture = new Texture(image, 0);

        mesh = createMesh(texture.getWidth(), texture.getHeight());

        texCoordOffset.addListener(shader);

        setFrame(0, 0);
    }

    public void setFrame(int frameX, int frameY) {

        float fx = texcoordX * (float) frameX;
        float fy = texcoordY * (float) frameY;

        texCoordOffset.setUniformData(fx, fy);
    }

    @Override
    public void render(int delta) {

        getShader().bind();
        texture.bind();
        mesh.draw();
        texture.unbind();
        getShader().unbind();
    }

    private Mesh createMesh(int width, int height) {

        float widthHeightRatio = (float) width / (float) height;
        float xRatio = widthHeightRatio / 2f;
        float yRatio = 1 - xRatio;

        float[] vertices = {
                -xRatio, yRatio, 0f,
                -xRatio, -yRatio, 0f,
                xRatio, -yRatio, 0f,
                xRatio, yRatio, 0f,
        };

        float[] normals = {
                0f, 0f, 1f,
                0f, 0f, 1f,
                0f, 0f, 1f,
                0f, 0f, 1f,
        };

        float[] texCoords = {
                0f, 0f,
                0f, texcoordY,
                texcoordX, texcoordY,
                texcoordX, 0f,
        };

        int[] indices = {0, 1, 2, 0, 2, 3,};

        List<VertexAttribute> attributes = new ArrayList<>();
        attributes.add(new StaticVertexAttribute(vertices, 3));
        attributes.add(new StaticVertexAttribute(normals, 3));
        attributes.add(new StaticVertexAttribute(texCoords, 2));

        return new Mesh(indices, attributes);
    }

    public int getCellsWide() {

        return cellsWide;
    }

    public int getCellsHigh() {

        return cellsHigh;
    }

}
