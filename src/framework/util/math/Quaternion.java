package framework.util.math;

/**
 * @author William Gervasio
 *         MAY HAVE AN ISSUE WITH GIMBAL LOCK WHEN CONVERTING TO EULER
 */
public class Quaternion {

    public float x, y, z, w;

    public Quaternion() {

        this(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public Quaternion(final float x, final float y, final float z, final float w) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public static void multiply(final Quaternion left, final Quaternion right, final Quaternion targer) {

        final float x = left.w * right.w - left.x * right.x - left.y * right.y - left.z * right.z;
        final float y = left.w * right.x + left.x * right.w + left.y * right.z - left.z * right.y;
        final float z = left.w * right.y - left.x * right.z + left.y * right.w + left.z * right.x;
        final float w = left.w * right.z + left.x * right.y - left.y * right.x + left.z * right.w;
        targer.x = x;
        targer.y = y;
        targer.z = z;
        targer.w = w;
    }

    public final float length2() {

        return x * x + y * y + z * z + w * w;
    }

    public final float length() {

        return (float) Math.sqrt(length2());
    }

    public final void inverse() {

        final float length = length2();

        x /= length;
        y /= length;
        z /= length;
        w /= length;
    }

    public final Quaternion normalize() {

        final float norm = (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);

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

    public Matrix4 computeMatrix() {

        final float sqx = x * x;
        final float sqy = y * y;
        final float sqz = z * z;
        final float sqw = w * w;

        final float inverseSquare = 1 / length2();

        final Matrix4 matrix = new Matrix4();

        matrix.data[Matrix4.M00] = (sqx - sqy - sqz + sqw) * inverseSquare;
        matrix.data[Matrix4.M11] = (-sqx + sqy - sqz + sqw) * inverseSquare;
        matrix.data[Matrix4.M22] = (-sqx - sqy + sqz + sqw) * inverseSquare;

        float temp1 = x * y;
        float temp2 = z * w;
        matrix.data[Matrix4.M10] = 2 * (temp1 + temp2) / inverseSquare;
        matrix.data[Matrix4.M01] = 2 * (temp1 - temp2) / inverseSquare;

        temp1 = x * z;
        temp2 = y * w;
        matrix.data[Matrix4.M20] = 2 * (temp1 - temp2) / inverseSquare;
        matrix.data[Matrix4.M02] = 2 * (temp1 + temp2) / inverseSquare;

        temp1 = y * z;
        temp2 = x * w;
        matrix.data[Matrix4.M21] = 2 * (temp1 + temp2) / inverseSquare;
        matrix.data[Matrix4.M12] = 2 * (temp1 - temp2) / inverseSquare;

        return matrix;
    }
}
