package framework.collision;

import framework.util.Region;
import framework.util.math.Vector2;

/**
 * Created by Will on 1/11/2015.
 */
public class AABB {

    private float x, y, halfWidth, halfHeight;
    private final Region region;

    public AABB(float x, float y, float width, float height) {

        this.x = x;
        this.y = y;

        halfWidth = width / 2;
        halfHeight = height / 2;

        region = new Region(x - halfWidth, y - halfHeight, x + halfWidth, y + halfHeight);
    }

    public void setWidth(float width) {

        halfWidth = width / 2;

        region.setLowerX(x - halfWidth);
        region.setUpperX(x + halfWidth);
    }

    public void setHeight(float height) {

        halfHeight = height / 2;

        region.setLowerY(y - halfHeight);
        region.setUpperY(y + halfHeight);
    }

    public void move(float x, float y) {

        region.shift(x, y);

        this.x += x;
        this.y += y;
    }

    public float getX() {

        return x;
    }

    public float getY() {

        return y;
    }

    public void setX(float x) {

        float xDifference = this.x - x;
        region.shift(xDifference, 0);

        this.x = x;
    }

    public void setY(float y) {

        float yDifference = this.y - y;
        region.shift(0, yDifference);

        this.y = y;
    }

    public float getWidth() {

        return region.getWidth();
    }

    public float getHeight() {

        return region.getHeight();
    }

    public float getLowerX() {

        return region.getLowerX();
    }

    public float getLowerY() {

        return region.getLowerY();
    }

    public float getUpperX() {

        return region.getUpperX();
    }

    public float getUpperY() {

        return region.getUpperY() ;
    }

    public Vector2 getLower() {

        return region.getLower();
    }

    public Vector2 getUpper() {

        return region.getUpper();
    }
}
