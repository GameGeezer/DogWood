package framework.collision;

import framework.util.RangeUtil;
import framework.util.Region;

/**
 * Created by Will on 12/18/2014.
 */
public class AABB {

    private Region region;

    public AABB(float x, float y, float width, float height) {
        float halfWidth  = width / 2;
        float halfHeight = height / 2;
        region = new Region(x - halfWidth, y - halfHeight, x + halfWidth, y + halfHeight);
    }

    public AABB(float width, float height) {
        this(0f, 0f, width, height);
    }

    public boolean collidesWith(AABB aabb) {
        if(RangeUtil.isWithinRange(aabb.getLowerX(), region.getLowerX(), region.getUpperX()) ||
                RangeUtil.isWithinRange(aabb.getUpperX(), region.getLowerX(), region.getUpperX())) {

            if(RangeUtil.isWithinRange(aabb.getLowerY(), region.getLowerY(), region.getUpperY()) ||
                    RangeUtil.isWithinRange(aabb.getUpperY(), region.getLowerY(), region.getUpperY())) {

                return true;
            }
        }
        return false;
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
        return region.getUpperY();
    }

    public float getWidth() {
        return region.getWidth();
    }

    public float getHeight() {
        return region.getHeight();
    }

    public float getX() {
        return region.findCenterX();
    }

    public float getY() {
        return region.findCenterY();
    }
}
