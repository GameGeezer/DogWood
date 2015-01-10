package framework.physics;

import framework.util.Region;
import framework.util.math.Vector2;

/**
 * Created by Will on 1/5/2015.
 */
public class AABB implements ICollidable {

    private float x, y, halfWidth, halfHeight;
    private Region region;

    public AABB(float x, float y, int width, int height) {

        this.x = x;
        this.y = y;

        halfWidth = width / 2;
        halfHeight = height / 2;

        region = new Region(x - halfWidth, y - halfHeight, x + halfWidth, x + halfHeight);
    }

    public boolean collidesWith(AABB aabb) {

        boolean overlapsX = region.getUpperX() > aabb.region.getLowerX() && region.getLowerX() < aabb.region.getUpperX();
        boolean overlapsY = region.getUpperY() > aabb.region.getLowerY() && region.getLowerY() < aabb.region.getUpperY();

        return overlapsX && overlapsY;
    }

    public boolean collidesWith(Circle circle) {

        return true;
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

    public float getWidth() {

        return region.getWidth();
    }

    public float getHeight() {

        return region.getHeight();
    }
}
