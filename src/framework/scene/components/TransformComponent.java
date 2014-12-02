package framework.scene.components;

import framework.util.math.Matrix4;
import framework.util.math.Quaternion;
import framework.util.math.Vector3;

/**
 * Created by Will on 11/25/2014.
 */
public class TransformComponent implements IStaticEntityComponent {

    private Matrix4 position = new Matrix4(), scale = new Matrix4(), model = new Matrix4();
    private Quaternion rotation = new Quaternion();

    public TransformComponent() {

    }

    public TransformComponent translate(float x, float y, float z) {
        position.data[Matrix4.M30] += x;
        position.data[Matrix4.M31] += y;
        position.data[Matrix4.M32] += z;

        return this;
    }

    public TransformComponent translate(Vector3 translation) {
       return translate(translation.getX(), translation.getY(), translation.getZ());
    }

    public TransformComponent setTranslation(float x, float y, float z) {
        position.data[Matrix4.M30] = x;
        position.data[Matrix4.M31] = y;
        position.data[Matrix4.M32] = z;

        return this;
    }

    public TransformComponent setTranslation(Vector3 translation) {
        return setTranslation(translation.getX(), translation.getY(), translation.getZ());
    }

    public TransformComponent setScale(float x, float y, float z) {
        scale.data[Matrix4.M00] = x;
        scale.data[Matrix4.M11] = y;
        scale.data[Matrix4.M22] = z;

        return this;
    }

    public TransformComponent setScale(Vector3 scale) {
        return setScale(scale.getX(), scale.getY(), scale.getZ());
    }

    public Matrix4 getModel() {
        model.set(rotation.getMatrix());
        Matrix4.multiply(model, position, model);

        return model;
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
