package framework.physics;

import java.util.ArrayList;

/**
 * Created by Will on 1/11/2015.
 */
public class CollisionGroup implements ICollidable{

    private ArrayList<AABB> aabbCollidables = new ArrayList<>();
    private ArrayList<Circle> circleCollidables = new ArrayList<>();

    public CollisionGroup() {

    }

    public void add(ICollidable collidable) {

        if(collidable instanceof AABB) {

            aabbCollidables.add((AABB) collidable);

        } else if(collidable instanceof Circle) {

            circleCollidables.add((Circle) collidable);
        }
    }

    public boolean collidesWith(AABB aabb) {

        boolean collisionFound = false;

        for(AABB collidable : aabbCollidables) {

            collisionFound = aabb.collidesWith(collidable);
        }

        for(Circle collidable : circleCollidables) {

            collisionFound = aabb.collidesWith(collidable);
        }

        return collisionFound;
    }

    public boolean collidesWith(Circle circle) {

        boolean collisionFound = false;

        for(AABB collidable : aabbCollidables) {

            collisionFound = circle.collidesWith(collidable);
        }

        for(Circle collidable : circleCollidables) {

            collisionFound = circle.collidesWith(collidable);
        }

        return collisionFound;
    }
}
