package framework.scene.components.collision;

import framework.collision.Circle;

/**
 * Created by Will on 1/11/2015.
 */
public class CircleComponent extends CollisionComponent {

    private Circle circle;

    public CircleComponent(float x, float y, float radius) {

        circle = new Circle(x, y, radius);

        float doubleRadius = radius * radius;
    }

    public void move(float x, float y) {

        circle.move(x, y);
    }

    public float getRadius() {

        return circle.getRadius();
    }

    public float getLocalX() {

        return circle.getX();
    }

    public float getLocalY() {

        return circle.getY();
    }

    public float getWorldX() {

        return getParentTransformX() + circle.getX();
    }

    public float getWorldY() {

        return getParentTransformY() + circle.getY();
    }
}
