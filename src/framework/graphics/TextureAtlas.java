package framework.graphics;

import framework.graphics.opengl.Texture;

/**
 * @author William Gervasio
 */
public class TextureAtlas {

    public final Texture texture;
    public final int cellWidth, cellHeight, cellsWide, cellsHigh;
    public final float textureCoordinateWidth, textureCoordinateHeight;

    public TextureAtlas(Image image, int cellWidth, int cellHeight) {

        this.texture = new Texture(image, 0);
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        cellsWide = (int) Math.floor(texture.width / cellWidth);
        cellsHigh = (int) Math.floor(texture.height / cellHeight);
        textureCoordinateWidth = (1.0f / (float) cellsWide);
        textureCoordinateHeight = (1.0f / (float) cellsHigh);
    }
}
