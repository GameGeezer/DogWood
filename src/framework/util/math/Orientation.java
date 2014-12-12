package framework.util.math;

/**
 * Created by Will on 12/6/2014.
 */
public class Orientation extends Quaternion {

    private Vector3 eulerRepresentation = new Vector3();

    public Orientation() {

    }

    public Orientation(float roll, float pitch, float yaw) {
        setEuler(roll, pitch, yaw);
    }

    public Orientation setEuler(float roll, float pitch, float yaw) {
        eulerRepresentation.set(roll, pitch, yaw);
        createFromEuler(eulerRepresentation.getX(), eulerRepresentation.getY(), eulerRepresentation.getZ());

        return this;
    }

    public Orientation setEuler(Vector3 euler) {
         return setEuler(euler.getX(), euler.getY(), euler.getZ());
    }

    public Orientation rotateEuler(float roll, float pitch, float yaw) {
        eulerRepresentation.add(roll, pitch, yaw);
        createFromEuler(eulerRepresentation.getX(), eulerRepresentation.getY(), eulerRepresentation.getZ());

        return this;
    }

    public Orientation rotateEuler(Vector3 euler) {
        return rotateEuler(euler.getX(), euler.getY(), euler.getZ());
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
            roll = 0;
            pitch = 2 * (float) Math.atan2(x, w);
            yaw = (float) Math.PI / 2;

            return new Vector3(roll, pitch, yaw);
        } else if (test < -0.499) { // singularity at south pole
            roll = 0;
            pitch = -2 * (float) Math.atan2(x, w);
            yaw = -(float) Math.PI / 2;

            return new Vector3(roll, pitch, yaw);
        }
        float sqx = x * x;
        float sqy = y * y;
        float sqz = z * z;
        roll = (float) Math.atan2(2 * x * w - 2 * y * z, 1 - 2 * sqx - 2 * sqz);
        pitch = (float) Math.atan2(2 * y * w - 2 * x * z, 1 - 2 * sqy - 2 * sqz);
        yaw = (float) Math.asin(2 * test);

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

        return (float) Math.asin(2 * test);
    }

    private void createFromEuler(float roll, float pitch, float yaw) {
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
    }

}
