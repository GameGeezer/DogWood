package framework.graphics;

import framework.graphics.opengl.Texture;
import framework.util.Region;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 12/21/2014.
 */
public class TextureAtlas {

    private Texture texture;
    private List<Region> regions = new ArrayList();

    public TextureAtlas(Texture texture) {

        this.texture = texture;
    }

    public TextureAtlas split(int width, int height) {

        if(texture.getWidth() % width != 0 || texture.getHeight() % height != 0)
            return this;

        int cellsWide = texture.getWidth() / width;
        int cellsHigh = texture.getHeight() / height;

        for(int i = 0; i < cellsWide; ++i) {

            for(int j = 0; j < cellsHigh; ++j) {

                float lowerX = i * width;
                float lowerY = j * height;
                float upperX = (i * width) + width;
                float upperY = (j * height) + height;

                regions.add(new Region(lowerX, lowerY, upperX, upperY));
            }
        }
        return this;
    }

    public TextureAtlas addRegion(float x1, float y1, float x2, float y2) {

        regions.add(new Region(x1, y1, x2, y2));

        return this;
    }

    public TextureAtlas clearRegions() {

        regions.clear();

        return this;
    }

    public Region getRegion(int index) {

        return regions.get(index);
    }
}
