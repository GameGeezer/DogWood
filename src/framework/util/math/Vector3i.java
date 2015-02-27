package framework.util.math;

/**
 * @author William Gervasio
 */
public class Vector3i implements Cloneable {

    public int x, y, z;

    public Vector3i() {

        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3i(final int x, final int y, final int z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final Vector3i set(final int x, final int y, final int z) {

        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public final Vector3i set(final Vector3i other) {

        this.x = other.x;
        this.y = other.y;
        this.z = other.z;

        return this;
    }

    public final Vector3i add(final int x, final int y, final int z) {

        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public static Vector3i add(final Vector3i left, final Vector3i right, final Vector3i result) {

        result.x = left.x + right.x;
        result.y = left.y + right.y;
        result.z = left.z + right.z;

        return result;
    }

    public final Vector3i sub(final int x, final int y, final int z) {

        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    public static Vector3i sub(final Vector3i left, final Vector3i right, final Vector3i result) {

        result.x = left.x - right.x;
        result.y = left.y - right.y;
        result.z = left.z - right.z;

        return result;
    }

    public final Vector3i mul(final int x, final int y, final int z) {

        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    public static Vector3i mul(final Vector3i left, final Vector3i right, final Vector3i result) {

        result.x = left.x * right.x;
        result.y = left.y * right.y;
        result.z = left.z * right.z;

        return result;
    }

    public final Vector3i div(final int x, final int y, final int z) {

        this.x /= x;
        this.y /= y;
        this.z /= z;

        return this;
    }

    public static Vector3f div(final Vector3i left, final Vector3i right, final Vector3f result) {

        result.x = (float) left.x / (float) right.x;
        result.y = (float) left.y / (float) right.y;
        result.z = (float) left.z / (float) right.z;

        return result;
    }

    public final Vector3i scale(final int scalar) {

        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;

        return this;
    }

    public static int dot(final Vector3i vector1, final Vector3i vector2) {

        return (vector1.x * vector2.x) + (vector1.y * vector2.y) + (vector1.z * vector2.z);
    }

    public final float length() {

        return (float) Math.sqrt(length2());
    }

    public final int length2() {

        return x * x + y * y + z * z;
    }

    public final Vector3i invert() {

        this.x = -x;
        this.y = -y;
        this.z = -z;

        return this;
    }

    public Vector3i clone() {

        return new Vector3i(x, y, z);
    }

    public int[] getElements() {

        return new int[]{x, y, z};
    }
}
