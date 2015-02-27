package framework.util.math;

/**
 * @author William Gervasio
 */
public class Vector3f implements Cloneable {

    public float x, y, z;

    public Vector3f() {

        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3f(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final Vector3f set(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public final Vector3f set(Vector3f other) {

        this.x = other.x;
        this.y = other.y;
        this.z = other.z;

        return this;
    }

    public final Vector3f add(float x, float y, float z) {

        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public static final Vector3f add(Vector3f left, Vector3f right, Vector3f result) {

        result.x = left.x + right.x;
        result.y = left.y + right.y;
        result.z = left.z + right.z;

        return result;
    }

    public final Vector3f sub(float x, float y, float z) {

        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    public static final Vector3f sub(Vector3f left, Vector3f right, Vector3f result) {

        result.x = left.x - right.x;
        result.y = left.y - right.y;
        result.z = left.z - right.z;

        return result;
    }

    public final Vector3f mul(float x, float y, float z) {

        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    public static final Vector3f mul(Vector3f left, Vector3f right, Vector3f result) {

        result.x = left.x * right.x;
        result.y = left.y * right.y;
        result.z = left.z * right.z;

        return result;
    }

    public final Vector3f div(float x, float y, float z) {

        this.x /= x;
        this.y /= y;
        this.z /= z;

        return this;
    }

    public static final Vector3f div(Vector3f left, Vector3f right, Vector3f result) {

        result.x = left.x * right.x;
        result.y = left.y * right.y;
        result.z = left.z * right.z;

        return result;
    }

    public final Vector3f scale(float scalar) {

        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;

        return this;
    }

    public static final float dot(Vector3f vector1, Vector3f vector2) {

        return (vector1.x * vector2.x) + (vector1.y * vector2.y) + (vector1.z * vector2.z);
    }

    public final float length() {

        return (float) Math.sqrt(length2());
    }

    public final float length2() {

        return x * x + y * y + z * z;
    }

    public final Vector3f invert() {

        this.x = -x;
        this.y = -y;
        this.z = -z;

        return this;
    }

    public Vector3f clone() {

        return new Vector3f(x, y, z);
    }

    public Float[] getElements() {

        return new Float[]{x, y, z};
    }
}
