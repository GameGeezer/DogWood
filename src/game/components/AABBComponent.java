package game.components;

import framework.collision.AABB;
import framework.scene.components.CollisionComponent;

/**
 * @author William Gervasio
 */
public class AABBComponent extends CollisionComponent {

    private AABB collisionBox;

    public AABBComponent(float x, float y, float width ,float height) {
        collisionBox = new AABB(x, y, width, height);
    }

}
