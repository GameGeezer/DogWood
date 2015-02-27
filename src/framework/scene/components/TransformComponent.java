package framework.scene.components;

import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.uniform.IUniformWrapper;
import framework.graphics.opengl.uniform.MatrixUniform;
import framework.scene.Entity.EntityComponent;
import framework.util.exceptions.EntityException;
import framework.util.math.Transform;
import framework.util.math.Matrix4;
import framework.util.math.Vector3f;

/**
 * Created by Will on 11/25/2014.
 */
public class TransformComponent extends EntityComponent implements IUniformWrapper {

    public static final String MODEL_UNIFORM = "u_modelMatrix";

    private MatrixUniform modelUniform = new MatrixUniform(MODEL_UNIFORM, MatrixUniform.MatrixUniformType.MATRIX4);

    private Transform transform = new Transform();
    private Matrix4 model = new Matrix4();

    public TransformComponent() {

        updateModelUniform();
    }

    public TransformComponent(Transform other) {

        set(other);
    }

    @Override
    protected void onAttach() throws EntityException {

        if (getParent().getComponentsOfType(TransformComponent.class).size() > 1) {

            removeSelfFromParent();

            throw new EntityException("Only one TransformComponent may be attached to an entity");
        }
    }

    @Override
    protected void onDetach() {

    }

    @Override
    protected void onComponentAttachedToParent(EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(EntityComponent component) {

    }

    public TransformComponent set(Transform other) {

        transform.setTranslation(other.getX(), other.getY(), other.getZ());
        transform.setScale(other.getScaleX(), other.getScaleY(), other.getScaleZ());
        transform.setOrientationEuler(other.getRoll(), other.getPitch(), other.getYaw());

        updateModelUniform();
        return this;
    }

    public TransformComponent translate(float x, float y, float z) {

        transform.translate(x, y, z);
        updateModelUniform();

        return this;
    }

    public TransformComponent translate(Vector3f translation) {

        return translate(translation.x, translation.y, translation.z);
    }

    public TransformComponent setTranslation(float x, float y, float z) {

        transform.setTranslation(x, y, z);
        updateModelUniform();

        return this;
    }

    public TransformComponent setTranslation(Vector3f translation) {

        return setTranslation(translation.x, translation.y, translation.z);
    }

    public TransformComponent setScale(float x, float y, float z) {

        transform.setScale(x, y, z);
        updateModelUniform();

        return this;
    }

    public TransformComponent setScale(Vector3f scale) {

        return setScale(scale.x, scale.y, scale.z);
    }

    public TransformComponent setOrientationEuler(float roll, float pitch, float yaw) {

        transform.setOrientationEuler(roll, pitch, yaw);
        updateModelUniform();

        return this;
    }

    public TransformComponent rotateEuler(float roll, float pitch, float yaw) {

        transform.rotateEuler(roll, pitch, yaw);
        updateModelUniform();

        return this;
    }

    public TransformComponent rotateEuler(Vector3f euler) {

        return rotateEuler(euler.x, euler.y, euler.z);
    }

    public Transform getTransform() {

        return transform;
    }

    public float getX() {

        return transform.getX();
    }

    public float getY() {

        return transform.getY();
    }

    public float getZ() {

        return transform.getZ();
    }

    public void setX(float x) {

        transform.setX(x);
        updateModelUniform();
    }

    public void setY(float y) {

        transform.setY(y);
        updateModelUniform();
    }

    public void setZ(float z) {

        transform.setZ(z);
        updateModelUniform();
    }


    public float getScaleX() {

        return transform.getScaleX();
    }

    public float getScaleY() {
        return transform.getScaleY();
    }

    public float getScaleZ() {

        return transform.getScaleZ();
    }

    @Override
    public void addListener(ShaderProgram shader) {

        modelUniform.addListener(shader);
    }

    @Override
    public void removeListener(ShaderProgram shader) {

        modelUniform.removeListener(shader);
    }

    @Override
    public TransformComponent clone() {

        TransformComponent component = new TransformComponent();

        component.setTranslation(getX(), getY(), getZ());
        component.setScale(getScaleX(), getScaleY(), getScaleZ());
        component.rotateEuler(transform.getRoll(), transform.getPitch(), transform.getYaw());

        return component;
    }

    private void updateModelUniform() {

        model.set(transform.getScale());

        Matrix4.multiply(model, transform.getOrientation().computeMatrix(), model);
        Matrix4.multiply(model, transform.getPosition(), model);

        modelUniform.setUniformData(model.data);
    }
}
