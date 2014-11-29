package framework.collision;

import framework.util.math.Vector2;

/**
 * Created by Will on 11/24/2014.
 */
public class AABB implements Collidable{

    private Vector2 position, dimensions;

    public AABB(Vector2 position, Vector2 dimensions) {
        this.position = position;
        this.dimensions = dimensions;
    }

    public boolean intersects(Ray ray) {
        return false;
    }

    public boolean intersects(AABB aabb) {
        //intersect along the x axis
        if((getX() >= aabb.getX() && getX() <= aabb.getX() + aabb.getWidth()) ||
                (getX() + getWidth() >= aabb.getX() && getX() + getWidth() <= aabb.getX() + aabb.getWidth())) {
            //intersect along thr y axis
            if((getY() >= aabb.getY() && getY() <= aabb.getY() + aabb.getHeight()) ||
                    (getY() + getHeight() >= aabb.getY() && getY() + getHeight() <= aabb.getY() + aabb.getHeight())) {
                return true;
            }
        }
        return false;
    }

    public float getWidth() {
        return dimensions.getX();
    }

    public float getHeight() {
        return dimensions.getX();
    }

    public float getX() {
        return position.getX();
    }

    public float getY() {
        return position.getY();
    }
}
