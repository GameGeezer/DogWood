package game.components;

import framework.scene.Entity;
import framework.scene.Transform;
import framework.scene.components.IEntityComponent;
import framework.util.math.Matrix4;

/**
 * Created by Will on 11/25/2014.
 */
public class TransformComponent extends IEntityComponent {

    private Transform transform;

    private TransformComponent parentTransfrom = null;
    private Matrix4 model = new Matrix4();

    public TransformComponent(Entity parent) {
        this(parent, null);
    }

    public TransformComponent(Entity parent, TransformComponent parentTransfrom) {
        super(parent);
        setParentTransform(parentTransfrom);
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
        Matrix4.multiply(model, transform.getPosition(), model);

        if(hasParentTransform()) {
            Matrix4 parentMatrix = parentTransfrom.createModel();
            Matrix4.multiply(parentMatrix, model, model);
            return model;
        }
        return model;
    }
}
