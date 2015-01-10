package framework.graphics;

import framework.graphics.opengl.Texture;
import framework.util.math.Matrix4;

/**
 * Created by Will on 12/27/2014.
 */
public class TextureAtlas {

    private Texture texture;
    private Matrix4 textureMatrix = new Matrix4();
    private int cellWidth, cellHeight, cellsWide, cellsHigh;
    private float textureCoordinateWidth, textureCoordinateHeight;

    public TextureAtlas(Texture texture, int cellWidth, int cellHeight) {
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        cellsWide = (int) Math.floor(texture.getWidth() / cellWidth);
        cellsHigh = (int) Math.floor(texture.getHeight() / cellHeight);
        textureCoordinateWidth = (1.0f / (float) cellsWide);
        textureCoordinateHeight = (1.0f / (float) cellsHigh);
    }

    public void selectCell(int cell) {

    }

    public void bindTexture() {
        texture.bind();
    }

    public void unbindTexture() {
        texture.unbind();
    }
}
