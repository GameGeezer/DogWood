package framework.scene.components.physics;

import framework.physics.AABB;
import framework.scene.components.CollisionComponent;

/**
 * Created by Will on 1/10/2015.
 */
public class AABBComponent extends CollisionComponent {

    private AABB aabb;

    public AABBComponent(float relativeX, float relativeY, float width, float height) {

        aabb = new AABB(relativeX, relativeY, width, height);
    }

    public void move(float x, float y) {

        aabb.move(x, y);
    }

    public float getWidth() {

        return aabb.getWidth();
    }

    public void setWidth(float width) {

        aabb.setWidth(width);
    }

    public float getHeight() {

        return aabb.getHeight();
    }

    public void setHeight(float height) {

        aabb.setHeight(height);
    }

    public float getLowerX() {

        return aabb.getLowerX() + getWorldX();
    }

    public float getLowerY() {

        return aabb.getLowerY() + getWorldY();
    }

    public float getUpperX() {

        return aabb.getUpperX() + getWorldX();
    }

    public float getUpperY() {

        return aabb.getUpperY() + getWorldY();
    }

    public float getLocalX() {

        return aabb.getX();
    }

    public void setLocalX(float x) {

        aabb.setX(x);
    }

    public float getLocalY() {

        return aabb.getY();
    }

    public void setLocalY(float y) {

        aabb.setY(y);
    }

    public float getWorldX() {

        return getParentTransformX() + aabb.getX();
    }

    public float getWorldY() {

        return getParentTransformY() + aabb.getY();
    }
}
