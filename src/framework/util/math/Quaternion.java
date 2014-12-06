package framework.util.math;

/**
 * @author William Gervasio
 * MAY HAVE AN ISSUE WITH GIMBAL LOCK WHEN CONVERTING TO EULER
 */
public class Quaternion {

    private float x = 0f, y = 0f, z = 0f, w = 1f;

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


    public Quaternion setEuler(float roll, float pitch, float yaw) {
        float cr, cp, cy, sr, sp, sy, cpcy, spsy;

        cr = (float) Math.cos(roll / 2);
        cp = (float) Math.cos(pitch / 2);
        cy = (float) Math.cos(yaw / 2);
        sr = (float) Math.sin(roll / 2);
        sp = (float) Math.sin(pitch / 2);
        sy = (float) Math.sin(yaw / 2);
        cpcy = cp * cy;
        spsy = sp * sy;
        w = cr * cpcy + sr * spsy;
        x = sr * cpcy - cr * spsy;
        y = cr * sp * cy + sr * cp * sy;
        z = cr * cp * sy - sr * sp * cy;

        return this;
    }

    public Quaternion setEuler(Vector3 euler) {
        return setEuler(euler.getX(), euler.getY(), euler.getZ());
    }

    public Matrix4 computeMatrix() {

        float sqx = x * x;
        float sqy = y * y;
        float sqz = z * z;
        float sqw = w * w;

        float inverseSquare = 1 / length2();

        Matrix4 matrix = new Matrix4();

        matrix.data[Matrix4.M00] = ( sqx - sqy -sqz + sqw) * inverseSquare;
        matrix.data[Matrix4.M11] = (-sqx + sqy -sqz + sqw) * inverseSquare;
        matrix.data[Matrix4.M22] = (-sqx - sqy +sqz + sqw) * inverseSquare;

        float temp1 = x * y;
        float temp2 = z * w;
        matrix.data[Matrix4.M10] = 2 * (temp1 + temp2) / inverseSquare;
        matrix.data[Matrix4.M01] = 2 * (temp1 - temp2) / inverseSquare;

        temp1 = x * z;
        temp2 = y * w;
        matrix.data[Matrix4.M20] = 2 * (temp1 + temp2) / inverseSquare;
        matrix.data[Matrix4.M02] = 2 * (temp1 - temp2) / inverseSquare;

        temp1 = y * z;
        temp2 = x * w;
        matrix.data[Matrix4.M30] = 2 * (temp1 + temp2) / inverseSquare;
        matrix.data[Matrix4.M03] = 2 * (temp1 - temp2) / inverseSquare;

        return matrix;
    }

    public Vector3 computeEulerAngles() {
        float roll, pitch, yaw;
        float test = x * y + z * w;
        if (test > 0.499) { // singularity at north pole
            pitch = 2 * (float) Math.atan2(x, w);
            yaw = (float) Math.PI / 2;
            roll = 0;
            return new Vector3(roll, pitch, yaw);
        } else if (test < -0.499) { // singularity at south pole
            pitch = -2 * (float) Math.atan2(x, w);
            yaw = -(float) Math.PI / 2;
            roll = 0;
            return new Vector3(roll, pitch, yaw);
        }
        float sqx = x * x;
        float sqy = y * y;
        float sqz = z * z;
        pitch = (float) Math.atan2(2 * y * w - 2 * x * z, 1 - 2 * sqy - 2 * sqz);
        yaw = (float) -Math.asin(2 * test);
        roll = (float) Math.atan2(2 * x * w - 2 * y * z, 1 - 2 * sqx - 2 * sqz);

        return new Vector3(roll, pitch, yaw);

    }

    public float computeRoll() {
        float test = x * y + z * w;

        if (test > 0.499 || test < -0.499) {
            return (float) 0;
        }

        return (float) Math.atan2(2 * x * w - 2 * y * z, 1 - 2 * x * x - 2 * z * z);
    }

    public float computePitch() {
        float test = x * y + z * w;

        if (test > 0.499 || test < -0.499) {
            return (float) Math.atan2(x, w) * Math.signum(test) * 2;
        }

        return (float) Math.atan2(2 * y * w - 2 * x * z, 1 - 2 * y * y - 2 * z * z);
    }

    public float computeYaw() {
        float test = x * y + z * w;

        if (test > 0.499 || test < -0.499) {
            return (float) (Math.PI / 2) * Math.signum(test);
        }

        return (float) -Math.asin(2 * test);
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
