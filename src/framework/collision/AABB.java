package framework.collision;

/**
 * Created by Will on 12/18/2014.
 */
public class AABB {

    private float x, y, width, height, halfWidth, halfHeight;

    public AABB(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.halfWidth = width / 2;
        this.halfHeight = height / 2;
    }

    public AABB(float width, float height) {
        this(0f, 0f, width, height);
    }

    public boolean collidesWith(AABB aabb) {
        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
