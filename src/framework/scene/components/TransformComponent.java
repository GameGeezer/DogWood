package framework.scene.components;

import framework.util.math.Quaternion;
import framework.util.math.Vector2;
import framework.util.math.Vector3;

/**
 * Created by Will on 11/25/2014.
 */
public class TransformComponent implements IStaticEntityComponent {

    private Vector3 position = new Vector3(), scale = new Vector3();
    private Quaternion rotation;

    public TransformComponent() {

    }

    public TransformComponent translate(float x, float y, float z) {
        position.add(x, y, z);

        return this;
    }

    public TransformComponent translate(Vector3 translation) {
        position.add(translation);

        return this;
    }

    public TransformComponent setTranslation(float x, float y, float z) {
        position.set(x, y, z);

        return this;
    }

    public TransformComponent setTranslation(Vector3 translation) {
        position.set(translation);

        return this;
    }

    public void setX(float value) {
        position.setX(value);
    }

    public float getX() {
        return position.getX();
    }

    public void setY(float value) {
        position.setY(value);
    }

    public float getY() {
        return position.getY();
    }

    public void setZ(float value) {
        position.setZ(value);
    }

    public float getZ() {
        return position.getZ();
    }
}
