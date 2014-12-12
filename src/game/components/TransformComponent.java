package game.components;

import framework.scene.Entity;
import framework.scene.Transform;
import framework.scene.components.IEntityComponent;
import framework.util.math.Matrix4;
import framework.util.math.Vector3;

/**
 * Created by Will on 11/25/2014.
 */
public class TransformComponent extends IEntityComponent {

    private Transform transform = new Transform();

    private TransformComponent parentTransfrom = null;
    private Matrix4 model = new Matrix4();

    public TransformComponent(Entity parent) {
        this(parent, null);
    }

    public TransformComponent(Entity parent, TransformComponent parentTransfrom) {
        super(parent);
        setParentTransform(parentTransfrom);
    }

    public TransformComponent translate(float x, float y, float z) {
        transform.translate(x, y, z);

        return this;
    }

    public TransformComponent translate(Vector3 translation) {
        return translate(translation.getX(), translation.getY(), translation.getZ());
    }

    public TransformComponent setTranslation(float x, float y, float z) {
        transform.setTranslation(x, y, z);

        return this;
    }

    public TransformComponent setTranslation(Vector3 translation) {
        return setTranslation(translation.getX(), translation.getY(), translation.getZ());
    }

    public TransformComponent setScale(float x, float y, float z) {
        transform.setScale(x, y, z);

        return this;
    }

    public TransformComponent setScale(Vector3 scale) {
        return setScale(scale.getX(), scale.getY(), scale.getZ());
    }

    public void setParentTransform(TransformComponent parent) {
        this.parentTransfrom = parent;
    }

    public TransformComponent getParentTransform() {
        return parentTransfrom;
    }

    public boolean hasParentTransform() {
        return parentTransfrom != null;
    }

    public Matrix4 createModel() {
        model.set(transform.getRotation().computeMatrix());
        Matrix4.multiply(model, transform.getScale(), model);
        Matrix4.multiply(model, transform.getPosition(), model);

        if(hasParentTransform()) {
            Matrix4 parentMatrix = parentTransfrom.createModel();
            Matrix4.multiply(parentMatrix, model, model);
            return model;
        }
        return model;
    }
}
