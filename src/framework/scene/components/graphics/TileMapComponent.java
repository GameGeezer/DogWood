package framework.scene.components.graphics;

import framework.graphics.Mesh;
import framework.graphics.TextureAtlas;
import framework.graphics.opengl.ShaderProgram;
import framework.util.Grid2D;
import framework.util.MeshBuilder;


/**
 * @author William Gervasio
 */
public class TileMapComponent extends RenderComponent{

    private static final String POSITION_COMPONENT = "POSITION";
    private static final String TEXTURE_COMPONENT = "TEXCOORD";
    private static final String NORMAL_COMPONENT = "NORMAL";

    private static final int POSITION_SIZE = 3;
    private static final int TEXTURE_SIZE = 2;
    private static final int NORMAL_SIZE = 3;

    private Mesh mapMesh;
    private TextureAtlas textureAtlas;

    public TileMapComponent(final ShaderProgram shader, Grid2D<Integer> map, TextureAtlas textureAtlas) {
        super(shader);

        this.textureAtlas = textureAtlas;
        mapMesh = createMesh(map, textureAtlas);
    }

    @Override
    public void render(int delta) {

        textureAtlas.texture.bind();
        getShader().bind();
        mapMesh.draw();
        getShader().unbind();
        textureAtlas.texture.unbind();
    }

    private Mesh createMesh(final Grid2D<Integer> map, final TextureAtlas textureAtlas) {

        final MeshBuilder builder = new MeshBuilder();

        builder.createComponent(POSITION_COMPONENT, POSITION_SIZE);
        builder.createComponent(NORMAL_COMPONENT, NORMAL_SIZE);
        builder.createComponent(TEXTURE_COMPONENT, TEXTURE_SIZE);

        int vertexIndex = 0;
        for(int x = 0; x < map.getLength(); ++x) {

            for(int y = 0; y < map.getHeight(); ++y) {

                builder.addToComponent(POSITION_COMPONENT, x, y, 0);
                builder.addToComponent(POSITION_COMPONENT, x + 1, y, 0);
                builder.addToComponent(POSITION_COMPONENT, x, y + 1, 0);
                builder.addToComponent(POSITION_COMPONENT, x + 1, y + 1, 0);

                float cellX = (float)map.get(x, y) % (float)textureAtlas.cellsWide;
                float cellY = (float) Math.floor((float)map.get(x, y) / (float)textureAtlas.cellsWide);

                float minTexX = cellX * textureAtlas.textureCoordinateWidth;
                float maxTexX = minTexX + textureAtlas.textureCoordinateWidth;

                float minTexY = cellY * textureAtlas.textureCoordinateHeight;
                float maxTexY = minTexY + textureAtlas.textureCoordinateHeight;

                builder.addToComponent(TEXTURE_COMPONENT, minTexX, minTexY);
                builder.addToComponent(TEXTURE_COMPONENT, minTexX, maxTexY);
                builder.addToComponent(TEXTURE_COMPONENT, maxTexX, minTexY);
                builder.addToComponent(TEXTURE_COMPONENT, maxTexX, maxTexY);

                builder.addToComponent(NORMAL_COMPONENT, 0, 1, 0);
                builder.addToComponent(NORMAL_COMPONENT, 0, 1, 0);
                builder.addToComponent(NORMAL_COMPONENT, 0, 1, 0);
                builder.addToComponent(NORMAL_COMPONENT, 0, 1, 0);

                builder.addIndex(vertexIndex);
                builder.addIndex(vertexIndex + 1);
                builder.addIndex(vertexIndex + 2);
                builder.addIndex(vertexIndex + 1);
                builder.addIndex(vertexIndex + 2);
                builder.addIndex(vertexIndex + 3);

                vertexIndex += 4;
            }
        }

        return builder.build();
    }
}
