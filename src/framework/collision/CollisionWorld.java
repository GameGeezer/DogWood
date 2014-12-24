package framework.collision;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 12/23/2014.
 */
public class CollisionWorld {

    private static List<ICollidable> collidables = new ArrayList<>();

    public static void addCollidable(ICollidable collidable) {
        collidables.add(collidable);
    }

    public static List<ICollidable> checkForCollisions(ICollidable checkingCollidable) {
        List<ICollidable> collisions = new ArrayList<>();

        for(ICollidable targetCollidable : collidables) {
            if(targetCollidable instanceof AABB && checkingCollidable.collidesWith((AABB) targetCollidable)) {
                collisions.add(targetCollidable);
            }
        }

        return collisions;
    }
}
