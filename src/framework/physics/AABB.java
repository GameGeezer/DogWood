package framework.physics;

/**
 * Created by Will on 1/5/2015.
 */
public class AABB implements ICollidable{

    private float x, y, width, height, halfWidth, halfHeight;

    public AABB(float x, float y, int width, int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.halfWidth = width / 2;
        this.halfHeight = height / 2;
    }

    public boolean collidesWith(AABB aabb) {
        return true;
    }

    public boolean collidesWith(Circle circle) {
        return true;
    }
}
