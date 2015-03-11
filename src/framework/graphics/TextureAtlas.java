package framework.graphics;

import framework.graphics.opengl.Texture;

/**
 * @author William Gervasio
 */
public class TextureAtlas {

    private Texture texture;
    private int cellWidth, cellHeight, cellsWide, cellsHigh;
    private float textureCoordinateWidth, textureCoordinateHeight;

    public TextureAtlas(Image image, int cellWidth, int cellHeight) {

        this.texture = new Texture(image, 0);
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        cellsWide = (int) Math.floor(texture.getWidth() / cellWidth);
        cellsHigh = (int) Math.floor(texture.getHeight() / cellHeight);
        textureCoordinateWidth = (1.0f / (float) cellsWide);
        textureCoordinateHeight = (1.0f / (float) cellsHigh);
    }

    public void bindTexture() {
        texture.bind();
    }

    public void unbindTexture() {
        texture.unbind();
    }

    public int getCellsWide() {

        return cellsWide;
    }

    public int getCellsHigh() {

        return  cellsHigh;
    }

    public float getTextureCoordinateWidth() {

        return textureCoordinateWidth;
    }

    public float getTextureCoordinateHeight() {

        return textureCoordinateHeight;
    }
}
