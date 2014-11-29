package framework.collision;

/**
 * Created by Will on 11/27/2014.
 */
public interface Collidable {
    public abstract boolean intersects(Ray ray);
    public abstract boolean intersects(AABB aabb);
}
