package framework.util.math;

/**
 * @author William Gervasio
 *         MAY HAVE AN ISSUE WITH GIMBAL LOCK WHEN CONVERTING TO EULER
 */
public class Quaternion {

    protected float x = 0f, y = 0f, z = 0f, w = 1f;

    public Quaternion() {

    }

    public static void multiply(Quaternion left, Quaternion right, Quaternion targer) {
        float x = left.w * right.w - left.x * right.x - left.y * right.y - left.z * right.z;
        float y = left.w * right.x + left.x * right.w + left.y * right.z - left.z * right.y;
        float z = left.w * right.y - left.x * right.z + left.y * right.w + left.z * right.x;
        float w = left.w * right.z + left.x * right.y - left.y * right.x + left.z * right.w;

        targer.x = x;
        targer.y = y;
        targer.z = z;
        targer.w = w;
    }

    public float length2() {
        return x * x + y * y + z * z + w * w;
    }

    public float length() {
        return (float) Math.sqrt(length2());
    }

    public void inverse() {
        float length = length2();
        x /= length;
        y /= length;
        z /= length;
        w /= length;
    }

    public Quaternion normalize() {
        float norm = (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z
                * this.z + this.w * this.w);
        if (norm > 0.0f) {
            this.x /= norm;
            this.y /= norm;
            this.z /= norm;
            this.w /= norm;
        } else {
            this.x = (float) 0.0;
            this.y = (float) 0.0;
            this.z = (float) 0.0;
            this.w = (float) 1.0;
        }

        return this;
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

    public float getW() {
        return w;
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

    public void setW(float w) {
        this.w = w;
    }
}
