package framework.collision;

/**
 * Created by Will on 1/11/2015.
 * */
public class Circle {

    private float x, y, radius;

    public Circle(float x, float y, float radius) {

        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void move(float x, float y) {

        this.x += x;
        this.y += y;
    }

    public float getX() {

        return x;
    }

    public float getY() {

        return y;
    }

    public void setX(float x) {

        this.x = x;
    }

    public void setY(float y) {

        this.y = y;
    }

    public float getRadius() {

        return radius;
    }
}
