package framework.physics;

import framework.util.math.Vector2;

/**
 * Created by Will on 1/12/2015.
 */
public class CollisionUtil {

    public static boolean checkForCollisionAABBvsAABB(float lowerX1, float lowerY1, float upperX1, float upperY1, float lowerX2, float lowerY2, float upperX2, float upperY2) {

        boolean overlapsX = upperX1 > lowerX2 && lowerX1 < upperX2;
        boolean overlapsY = upperY1 > lowerY2 && lowerY1 < upperY2;

        return overlapsX && overlapsY;
    }

    public static boolean checkForCollisionAABBvsAABB(Vector2 lower1, Vector2 upper1, Vector2 lower2, Vector2 upper2) {

        boolean overlapsX = upper1.getX() > lower2.getX() && lower1.getX() < upper2.getX();
        boolean overlapsY = upper1.getY() > lower2.getY() && lower1.getY() < upper2.getY();

        return overlapsX && overlapsY;
    }

    public static boolean checkForCollisionAABBvsAABB(AABB aabb1, AABB aabb2) {

        return checkForCollisionAABBvsAABB(aabb1.getLower(), aabb1.getUpper(), aabb2.getLower(), aabb2.getUpper());
    }

    public static boolean checkForCollisionCirclevsCircle(Vector2 position1, float radius1, Vector2 position2, float radius2) {

        float xDifference = position1.getX() - position2.getX();
        float yDifference = position1.getY() - position2.getY();
        float radiusDifference = radius1 - radius2;

        return radiusDifference * radiusDifference > (xDifference * xDifference) + (yDifference * yDifference);
    }

    public static boolean checkForCollisionCirclevsCircle(Circle circle1, Circle circle2) {

        float xDifference = circle1.getX() - circle2.getX();
        float yDifference = circle1.getY() - circle2.getY();
        float radiusDifference = circle1.getRadius() - circle2.getRadius();

        return radiusDifference * radiusDifference > (xDifference * xDifference) + (yDifference * yDifference);
    }
}
