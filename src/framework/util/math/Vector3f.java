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

    public Vector3f(final float x, final float y, final float z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final Vector3f set(final float x, final float y, final float z) {

        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public final Vector3f set(final Vector3f other) {

        this.x = other.x;
        this.y = other.y;
        this.z = other.z;

        return this;
    }

    public final Vector3f add(final float x, final float y, final float z) {

        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public static Vector3f add(final Vector3f left, final Vector3f right, final Vector3f result) {

        result.x = left.x + right.x;
        result.y = left.y + right.y;
        result.z = left.z + right.z;

        return result;
    }

    public final Vector3f sub(final float x, final float y, final float z) {

        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    public static Vector3f sub(final Vector3f left, final Vector3f right, final Vector3f result) {

        result.x = left.x - right.x;
        result.y = left.y - right.y;
        result.z = left.z - right.z;

        return result;
    }

    public final Vector3f mul(final float x, final float y, final float z) {

        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    public static Vector3f mul(final Vector3f left, final Vector3f right, final Vector3f result) {

        result.x = left.x * right.x;
        result.y = left.y * right.y;
        result.z = left.z * right.z;

        return result;
    }

    public final Vector3f div(final float x, final float y, final float z) {

        this.x /= x;
        this.y /= y;
        this.z /= z;

        return this;
    }

    public static Vector3f div(final Vector3f left, final Vector3f right, final Vector3f result) {

        result.x = left.x * right.x;
        result.y = left.y * right.y;
        result.z = left.z * right.z;

        return result;
    }

    public final Vector3f scale(final float scalar) {

        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;

        return this;
    }

    public static float dot(final Vector3f vector1, final Vector3f vector2) {

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
