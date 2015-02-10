package framework.util.math;

/**
 * A transform stores an objects position, scale, and orientation
 *
 * @author William Gervasio
 */
public class Transform {

    private Matrix4 position = new Matrix4(), scale = new Matrix4();
    private Orientation orientation = new Orientation();

    public Transform() {

    }

    public Transform translate(float x, float y, float z) {

        position.data[Matrix4.M03] += x;
        position.data[Matrix4.M13] += y;
        position.data[Matrix4.M23] += z;

        return this;
    }

    public Transform translate(Vector3 translation) {

        return translate(translation.getX(), translation.getY(), translation.getZ());
    }

    public Transform setTranslation(float x, float y, float z) {

        position.data[Matrix4.M03] = x;
        position.data[Matrix4.M13] = y;
        position.data[Matrix4.M23] = z;

        return this;
    }

    public Transform setTranslation(Vector3 translation) {

        return setTranslation(translation.getX(), translation.getY(), translation.getZ());
    }

    public Transform setScale(float x, float y, float z) {

        scale.data[Matrix4.M00] = x;
        scale.data[Matrix4.M11] = y;
        scale.data[Matrix4.M22] = z;

        return this;
    }

    public Transform setScale(Vector3 scale) {

        return setScale(scale.getX(), scale.getY(), scale.getZ());
    }

    public Transform setOrientationEuler(float roll, float pitch, float yaw) {

        orientation.setEuler(roll, pitch, yaw);

        return this;
    }

    public Transform rotateEuler(float roll, float pitch, float yaw) {

        orientation.rotateEuler(roll, pitch, yaw);

        return this;
    }

    public Transform rotateEuler(Vector3 euler) {

        return rotateEuler(euler.getX(), euler.getY(), euler.getZ());
    }

    //rotation
    public Orientation getOrientation() {
        return orientation;
    }

    public float getRoll() {
        return orientation.getRoll();
    }

    public float getPitch() {
        return orientation.getPitch();
    }

    public float getYaw() {
        return orientation.getYaw();
    }

    //scale

    public Matrix4 getScale() {
        return scale;
    }

    public void setScaleX(float value) {
        scale.data[Matrix4.M00] = value;
    }

    public float getScaleX() {
        return scale.data[Matrix4.M00];
    }

    public void setScaleY(float value) {
        scale.data[Matrix4.M11] = value;
    }

    public float getScaleY() {
        return scale.data[Matrix4.M11];
    }

    public void setScaleZ(float value) {
        scale.data[Matrix4.M22] = value;
    }

    public float getScaleZ() {
        return scale.data[Matrix4.M22];
    }

    //position

    public Matrix4 getPosition() {
        return position;
    }

    public void setX(float value) {
        position.data[Matrix4.M03] = value;
    }

    public float getX() {
        return position.data[Matrix4.M03];
    }

    public void setY(float value) {
        position.data[Matrix4.M13] = value;
    }

    public float getY() {
        return position.data[Matrix4.M13];
    }

    public void setZ(float value) {
        position.data[Matrix4.M23] = value;
    }

    public float getZ() {
        return position.data[Matrix4.M23];
    }
}
