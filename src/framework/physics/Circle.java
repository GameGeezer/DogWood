package framework.physics;

/**
 * Created by Will on 1/6/2015.
 */
public class Circle implements ICollidable {

    private float x, y, radius;

    public boolean collidesWith(AABB aabb) {
        return false;
    }

    public boolean collidesWith(Circle circle) {

        float radiusDifference = radius - circle.radius;
        float xDifference = x - circle.x;
        float yDifference = y - circle.y;

        return radiusDifference * radiusDifference > (xDifference * xDifference) + (yDifference * yDifference);
    }
}
