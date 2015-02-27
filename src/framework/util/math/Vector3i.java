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

    public Vector3i(int x, int y, int z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final Vector3i set(int x, int y, int z) {

        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public final Vector3i set(Vector3i other) {

        this.x = other.x;
        this.y = other.y;
        this.z = other.z;

        return this;
    }

    public final Vector3i add(int x, int y, int z) {

        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public static final Vector3i add(Vector3i left, Vector3i right, Vector3i result) {

        result.x = left.x + right.x;
        result.y = left.y + right.y;
        result.z = left.z + right.z;

        return result;
    }

    public final Vector3i sub(int x, int y, int z) {

        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    public static final Vector3i sub(Vector3i left, Vector3i right, Vector3i result) {

        result.x = left.x - right.x;
        result.y = left.y - right.y;
        result.z = left.z - right.z;

        return result;
    }

    public final Vector3i mul(int x, int y, int z) {

        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    public static final Vector3i mul(Vector3i left, Vector3i right, Vector3i result) {

        result.x = left.x * right.x;
        result.y = left.y * right.y;
        result.z = left.z * right.z;

        return result;
    }

    public final Vector3i div(int x, int y, int z) {

        this.x /= x;
        this.y /= y;
        this.z /= z;

        return this;
    }

    public static final Vector3f div(Vector3i left, Vector3i right, Vector3f result) {

        result.x = (float) left.x / (float) right.x;
        result.y = (float) left.y / (float) right.y;
        result.z = (float) left.z / (float) right.z;

        return result;
    }

    public final Vector3i scale(int scalar) {

        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;

        return this;
    }

    public static final int dot(Vector3i vector1, Vector3i vector2) {

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
