package framework.collision;

/**
 * Created by Will on 12/23/2014.
 */
public interface ICollidable {

    public abstract boolean collidesWith(AABB aabb);

    public abstract float getX();

    public abstract float getY();
}
