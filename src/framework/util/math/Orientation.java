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

        matrix.data[Matrix4.M00] = (sqx - sqy - sqz + sqw) * inverseSquare;
        matrix.data[Matrix4.M11] = (-sqx + sqy - sqz + sqw) * inverseSquare;
        matrix.data[Matrix4.M22] = (-sqx - sqy + sqz + sqw) * inverseSquare;

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

    public float getRoll() {

        return eulerRepresentation.getX();
    }

    public float getPitch() {

        return eulerRepresentation.getY();
    }

    public float getYaw() {

        return eulerRepresentation.getZ();
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
