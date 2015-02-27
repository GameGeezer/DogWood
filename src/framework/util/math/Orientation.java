package framework.util.math;

/**
 * @author William Gervasio
 */
public class Orientation {

    private final Quaternion rotation = new Quaternion();
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

    public final Orientation setEuler(final Vector3f euler) {

        return setEuler(euler.x, euler.y, euler.z);
    }

    public final Orientation rotateEuler(final float roll, final float pitch, final float yaw) {

        eulerRepresentation.add(roll, pitch, yaw);

        createFromEuler(eulerRepresentation.x, eulerRepresentation.y, eulerRepresentation.z);

        return this;
    }

    public final Orientation rotateEuler(final Vector3f euler) {

        return rotateEuler(euler.x, euler.y, euler.z);
    }


    public final float getRoll() {

        return eulerRepresentation.x;
    }

    public final float getPitch() {

        return eulerRepresentation.y;
    }

    public final float getYaw() {

        return eulerRepresentation.z;
    }

    private void createFromEuler(float roll, float pitch, float yaw) {

        final float cr, cp, cy, sr, sp, sy;

        cr = (float) Math.cos(roll / 2);
        cp = (float) Math.cos(pitch / 2);
        cy = (float) Math.cos(yaw / 2);
        sr = (float) Math.sin(roll / 2);
        sp = (float) Math.sin(pitch / 2);
        sy = (float) Math.sin(yaw / 2);

        rotation.x = sy * sp * cr + cy * cp * sr;
        rotation.y = sy * cp * cr + cy * sp * sr;
        rotation.z = cy * sp * cr - sy * cp * sr;
        rotation.w = cr * cp * cy - sr * sp * sy;
    }

    public Matrix4 computeMatrix() {

        return rotation.computeMatrix();
    }
}
