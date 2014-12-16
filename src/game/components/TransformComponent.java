package game.components;

import framework.graphics.opengl.ShaderProgram;
import framework.graphics.uniform.MatrixUniform;
import framework.scene.Entity;
import framework.scene.Transform;
import framework.scene.components.EntityComponent;
import framework.util.math.Matrix4;
import framework.util.math.Vector3;
import game.IUniformWrapper;

/**
 * Created by Will on 11/25/2014.
 */
public class TransformComponent extends EntityComponent implements IUniformWrapper {

    public static final String MODEL_UNIFORM = "u_modelMatrix";

    private MatrixUniform modelUniform = new MatrixUniform(MODEL_UNIFORM, MatrixUniform.MatrixUniformType.MATRIX4);

    private Transform transform = new Transform();
    private Matrix4 model = new Matrix4();

    public TransformComponent(Entity parent) {
        super(parent);
        updateModelUniform();
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

    @Override
    public void addListener(ShaderProgram shader) {
        modelUniform.addListener(shader);
    }

    @Override
    public void removeListener(ShaderProgram shader) {
        modelUniform.removeListener(shader);
    }

    private void updateModelUniform() {
        model.set(transform.getOrientation().computeMatrix());
        Matrix4.multiply(model, transform.getPosition(), model);
        Matrix4.multiply(model, transform.getScale(), model);

        modelUniform.setUniformData(model.data);
    }
}
