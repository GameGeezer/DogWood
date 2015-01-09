package framework.physics;

/**
 * Created by Will on 1/6/2015.
 */
public interface ICollidable {

    public abstract boolean collidesWith(AABB aabb);

    public abstract boolean collidesWith(Circle circle);
}
