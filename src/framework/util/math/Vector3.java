package framework.util.math;

/**
 * @author William Gervasio
 */
public class Vector3 implements Cloneable {

    private float x, y, z;

    public Vector3() {
        set(0, 0, 0);
    }

    public Vector3(float x, float y, float z) {
        set(x, y, z);
    }

    public final Vector3 set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public final Vector3 set(Vector3 other) {
        return set(other.x, other.y, other.z);
    }

    public final Vector3 add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public final Vector3 add(Vector3 other) {
        return add(other.x, other.y, other.z);
    }

    public final Vector3 sub(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    public final Vector3 sub(Vector3 other) {
        return sub(other.x, other.y, other.z);
    }

    public final Vector3 mul(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    public final Vector3 mul(Vector3 other) {
        return mul(other.x, other.y, other.z);
    }

    public final Vector3 div(float x, float y, float z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;

        return this;
    }

    public final Vector3 div(Vector3 other) {
        return div(other.x, other.y, other.z);
    }

    public final Vector3 scale(float scalar) {
        return mul(scalar, scalar, scalar);
    }

    public final float dot(Vector3 other) {
        return (x * other.x) + (y * other.y + (z * other.z));
    }

    public final float length() {
        return (float) Math.sqrt(length2());
    }

    public final float length2() {
        return (float) (x * x + y * y + z * z);
    }

    public final Vector3 invert() {
        return set(-x, -y, -z);
    }

    public final Vector3 inverse() {
        return clone().invert();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3 clone() {
        return new Vector3(x, y, z);
    }

    public Float[] getElements() {
        return new Float[]{x, y, z};
    }
}
