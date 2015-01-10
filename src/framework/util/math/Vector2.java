package framework.util.math;

/**
 * @author William Gervasio
 */

public class Vector2 implements Cloneable {

    public static final Vector2 UP = new ImmutableVector2(0, 1);
    public static final Vector2 DOWN = new ImmutableVector2(0, -1);
    public static final Vector2 RIGHT = new ImmutableVector2(1, 0);
    public static final Vector2 LEFT = new ImmutableVector2(-1, 0);

    private float x, y;

    public Vector2() {

        set(0, 0);
    }

    public Vector2(float x, float y) {

        set(x, y);
    }

    public Vector2 set(float x, float y) {

        this.x = x;
        this.y = y;

        return this;
    }

    public Vector2 set(Vector2 other) {

        return set(other.x, other.y);
    }

    public Vector2 add(float x, float y) {

        this.x += x;
        this.y += y;

        return this;
    }

    public Vector2 add(Vector2 other) {

        return add(other.x, other.y);
    }

    public Vector2 sub(float x, float y) {

        this.x -= x;
        this.y -= y;

        return this;
    }

    public Vector2 sub(Vector2 other) {

        return sub(other.x, other.y);
    }

    public Vector2 mul(float x, float y) {

        this.x *= x;
        this.y *= y;

        return this;
    }

    public Vector2 mul(Vector2 other) {

        return mul(other.x, other.y);
    }

    public Vector2 div(float x, float y) {

        this.x /= x;
        this.y /= y;

        return this;
    }

    public Vector2 div(Vector2 other) {

        return div(other.x, other.y);
    }

    public Vector2 scale(float scalar) {

        return mul(scalar, scalar);
    }

    public float dot(Vector2 other) {

        return (x * other.getX()) + (y * other.getY());
    }

    public float length() {

        return (float) Math.sqrt(length2());
    }

    public float length2() {

        return x * x + y * y;
    }

    public Vector2 invert() {

        return set(-x, -y);
    }

    public Vector2 normalize() {

        final float length = length();

        x /= length;
        y /= length;

        return this;
    }

    public Vector2 rotate(Vector2 axis, double angle) {

        this.sub(axis);
        this.rotate(angle);
        this.add(axis);

        return this;
    }

    public Vector2 rotate(double angle) {

        final float xh = x;
        final float yh = y;

        x = (float) (xh * Math.cos(angle) - (yh * Math.sin(angle)));
        y = (float) (xh * Math.sin(angle) + (yh * Math.cos(angle)));

        return this;
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

    public Vector2 clone() {

        return new Vector2(x, y);
    }

    public static void projectAlongX(Vector2 vector, Vector2 result) {

        result.set(vector.dot(RIGHT), 0);
    }

    public static void projectAlongY(Vector2 vector, Vector2 result) {

        result.set(0, vector.dot(UP));
    }

    public static void project(Vector2 vector, Vector2 against, Vector2 result) {

        float dp = vector.dot(against);
        float dpByLength2 = dp / against.length2();

        float x = dpByLength2 * against.getX();
        float y = dpByLength2 * against.getY();

        result.set(x, y);
    }

    public static void findRightHandNormal(Vector2 vector, Vector2 resultVector) {

        resultVector.set(-vector.getY(), vector.getX());
    }

    public static void findLeftHandNormal(Vector2 vector, Vector2 resultVector) {

        resultVector.set(vector.getY(), -vector.getX());
    }

    public Float[] getElements() {

        return new Float[]{x, y};
    }
}
