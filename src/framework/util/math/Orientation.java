package framework.util.math;

/**
 * Created by Will on 12/6/2014.
 */
public class Orientation extends Quaternion {

    private final Vector3f eulerRepresentation = new Vector3f();

    public Orientation() {

    }

    public Orientation(float roll, float pitch, float yaw) {

        setEuler(roll, pitch, yaw);
    }

    public Orientation setEuler(float roll, float pitch, float yaw) {

        eulerRepresentation.set(roll, pitch, yaw);

        createFromEuler(eulerRepresentation.x, eulerRepresentation.y, eulerRepresentation.z);

        return this;
    }

    public Orientation setEuler(Vector3f euler) {

        return setEuler(euler.x, euler.y, euler.z);
    }

    public Orientation rotateEuler(float roll, float pitch, float yaw) {

        eulerRepresentation.add(roll, pitch, yaw);

        createFromEuler(eulerRepresentation.x, eulerRepresentation.y, eulerRepresentation.z);

        return this;
    }

    public Orientation rotateEuler(Vector3f euler) {

        return rotateEuler(euler.x, euler.y, euler.z);
    }

    public Matrix4 computeMatrix() {

        final float sqx = getX() * getX();
        final float sqy = getY() * getY();
        final float sqz = getZ() * getZ();
        final float sqw = getW() * getW();

        final float inverseSquare = 1 / length2();

        final Matrix4 matrix = new Matrix4();

        matrix.data[Matrix4.M00] = (sqx - sqy - sqz + sqw) * inverseSquare;
        matrix.data[Matrix4.M11] = (-sqx + sqy - sqz + sqw) * inverseSquare;
        matrix.data[Matrix4.M22] = (-sqx - sqy + sqz + sqw) * inverseSquare;

        float temp1 = getX() * getY();
        float temp2 = getZ() * getW();
        matrix.data[Matrix4.M10] = 2 * (temp1 + temp2) / inverseSquare;
        matrix.data[Matrix4.M01] = 2 * (temp1 - temp2) / inverseSquare;

        temp1 = getX() * getZ();
        temp2 = getY() * getW();
        matrix.data[Matrix4.M20] = 2 * (temp1 - temp2) / inverseSquare;
        matrix.data[Matrix4.M02] = 2 * (temp1 + temp2) / inverseSquare;

        temp1 = getY() * getZ();
        temp2 = getX() * getW();
        matrix.data[Matrix4.M21] = 2 * (temp1 + temp2) / inverseSquare;
        matrix.data[Matrix4.M12] = 2 * (temp1 - temp2) / inverseSquare;

        return matrix;
    }

    public float getRoll() {

        return eulerRepresentation.x;
    }

    public float getPitch() {

        return eulerRepresentation.y;
    }

    public float getYaw() {

        return eulerRepresentation.z;
    }

    private void createFromEuler(float roll, float pitch, float yaw) {

        float cr, cp, cy, sr, sp, sy;

        cr = (float) Math.cos(roll / 2);
        cp = (float) Math.cos(pitch / 2);
        cy = (float) Math.cos(yaw / 2);
        sr = (float) Math.sin(roll / 2);
        sp = (float) Math.sin(pitch / 2);
        sy = (float) Math.sin(yaw / 2);

        setX(sy * sp * cr + cy * cp * sr);
        setY(sy * cp * cr + cy * sp * sr);
        setZ(cy * sp * cr - sy * cp * sr);
        setW(cr * cp * cy - sr * sp * sy);
    }
}
