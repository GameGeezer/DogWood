package framework.scene;

import framework.util.math.Matrix4;
import framework.util.math.Orientation;
import framework.util.math.Quaternion;
import framework.util.math.Vector3;

/**
 * Created by Will on 12/6/2014.
 */
public class Transform {

    private Matrix4 position = new Matrix4(), scale = new Matrix4();
    private Orientation orientation = new Orientation();



    public Transform() {

    }

    public Transform translate(float x, float y, float z) {
        position.data[Matrix4.M30] += x;
        position.data[Matrix4.M31] += y;
        position.data[Matrix4.M32] += z;

        return this;
    }

    public Transform translate(Vector3 translation) {
        return translate(translation.getX(), translation.getY(), translation.getZ());
    }

    public Transform setTranslation(float x, float y, float z) {
        position.data[Matrix4.M30] = x;
        position.data[Matrix4.M31] = y;
        position.data[Matrix4.M32] = z;

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
/*
    public void setRotationX(float value) {
        scale.data[Matrix4.M00] = value;
    }

    public float getRoll() {
        return rotation.computeRoll();
    }

    public void setRotationY(float value) {
        scale.data[Matrix4.M11] = value;
    }

    public float getPitch() {
        return rotation.computePitch();
    }

    public void setRotationZ(float value) {
        scale.data[Matrix4.M22] = value;
    }

    public float getYaw() {
        return rotation.computeYaw();
    }
*/
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
        position.data[Matrix4.M30] = value;
    }

    public float getX() {
        return position.data[Matrix4.M30];
    }

    public void setY(float value) {
        position.data[Matrix4.M31] = value;
    }

    public float getY() {
        return position.data[Matrix4.M31];
    }

    public void setZ(float value) {
        position.data[Matrix4.M32] = value;
    }

    public float getZ() {
        return position.data[Matrix4.M32];
    }
}
