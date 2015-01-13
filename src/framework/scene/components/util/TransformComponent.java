package framework.scene.components.util;

import framework.graphics.opengl.ShaderProgram;
import framework.graphics.uniform.IUniformWrapper;
import framework.graphics.uniform.MatrixUniform;
import framework.scene.Entity.EntityComponent;
import framework.scene.components.UniformComponent;
import framework.util.exceptions.EntityException;
import framework.util.math.Transform;
import framework.util.math.Matrix4;
import framework.util.math.Vector3;

/**
 * Created by Will on 11/25/2014.
 */
public class TransformComponent extends UniformComponent implements IUniformWrapper {

    public static final String MODEL_UNIFORM = "u_modelMatrix";

    private MatrixUniform modelUniform = new MatrixUniform(MODEL_UNIFORM, MatrixUniform.MatrixUniformType.MATRIX4);

    private Transform transform = new Transform();
    private Matrix4 model = new Matrix4();

    public TransformComponent() {

        updateModelUniform();
    }

    @Override
    protected void onAttach() throws EntityException {

        if(getParent().getComponentsOfType(TransformComponent.class).size() > 1) {

            removeSelfFromParent();

            throw  new EntityException("Only one TransformComponent may be attached to an entity");
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

    public TransformComponent translate(float x, float y, float z) {

        transform.translate(x, y, z);
        updateModelUniform();

        return this;
    }

    public TransformComponent translate(Vector3 translation) {

        return translate(translation.getX(), translation.getY(), translation.getZ());
    }

    public TransformComponent setTranslation(float x, float y, float z) {

        transform.setTranslation(x, y, z);
        updateModelUniform();

        return this;
    }

    public TransformComponent setTranslation(Vector3 translation) {

        return setTranslation(translation.getX(), translation.getY(), translation.getZ());
    }

    public TransformComponent setScale(float x, float y, float z) {

        transform.setScale(x, y, z);
        updateModelUniform();

        return this;
    }

    public TransformComponent setScale(Vector3 scale) {

        return setScale(scale.getX(), scale.getY(), scale.getZ());
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

    public TransformComponent rotateEuler(Vector3 euler) {

        return rotateEuler(euler.getX(), euler.getY(), euler.getZ());
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
