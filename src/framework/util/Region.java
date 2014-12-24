package framework.util;

/**
 * Created by Will on 12/23/2014.
 */
public class Region {

    private float lowerX, lowerY, upperX, upperY, width, height;

    public Region(float x1, float y1, float x2, float y2) {

        this.lowerX = x1;
        this.lowerY = y1;
        this.upperX = x2;
        this.upperY = y2;

        ensureStateLegality();
        updateBounds();
    }

    public Region setLower(float x1, float y1) {

        this.lowerX = x1;
        this.lowerY = y1;

        ensureStateLegality();
        updateBounds();

        return this;
    }

    public Region setUpper(float x2, float y2) {

        this.upperX = x2;
        this.upperY = y2;

        ensureStateLegality();
        updateBounds();

        return this;
    }

    public Region shift(float x, float y) {

        this.lowerX += x;
        this.lowerY += y;
        this.upperX += x;
        this.upperY += y;

        updateBounds();

        return this;
    }

    public float getLowerX() {
        return lowerX;
    }

    public float getLowerY() {
        return lowerY;
    }

    public float getUpperX() {
        return upperX;
    }

    public float getUpperY() {
        return upperY;
    }

    public float findCenterX() {
        return lowerX + (width / 2);
    }

    public float findCenterY() {
        return lowerY + (height / 2);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    private void updateBounds() {

        width = Math.abs(upperX - lowerX);

        height = Math.abs(upperY - lowerY);
    }

    private void ensureStateLegality() {
        if(lowerX > upperX) {

            float x = lowerX;
            lowerX = upperX;
            upperX = x;
        }
        if(lowerY > upperY) {

            float y = lowerY;
            lowerY = upperY;
            upperY = y;
        }

    }
}
