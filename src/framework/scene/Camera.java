package framework.scene;

import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.uniform.IUniformWrapper;
import framework.graphics.opengl.uniform.MatrixUniform;
import framework.util.math.Matrix4;
import framework.util.math.Transform;
import framework.util.math.Vector3;

/**
 * Created by Will on 2/7/14.
 */
public class Camera implements IUniformWrapper {

    public static final String PROJECTION_UNIFORM = "u_projectionMatrix";
    public static final String VIEW_UNIFORM = "u_viewMatrix";

    private final Matrix4 view = new Matrix4(), projection = new Matrix4();
    private float width, height, near, far, fieldOfView;

    private final MatrixUniform projectionUniform = new MatrixUniform(PROJECTION_UNIFORM, MatrixUniform.MatrixUniformType.MATRIX4);
    private final MatrixUniform viewUniform = new MatrixUniform(VIEW_UNIFORM, MatrixUniform.MatrixUniformType.MATRIX4);

    private final Transform transform = new Transform();

    public Camera(float width, float height, float near, float far, float fieldOfView) {

        this.width = width;
        this.height = height;
        this.near = near;
        this.far = far;
        this.fieldOfView = fieldOfView;

        updateView();

        updateProjection();
    }

    public void move(float x, float y, float z) {

        transform.translate(-x, -y, -z);
        updateView();
    }

    public void rotate(float roll, float pitch, float yaw) {

        transform.rotateEuler(-roll, -pitch, -yaw);
        updateView();
    }

    /**
     * TODO
     *
     * @param point
     */
    public void lookAt(Vector3 point) {

    }

    public Matrix4 getView() {
        return view;
    }

    public Matrix4 getProjection() {
        return projection;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getNear() {
        return near;
    }

    public float getFar() {
        return far;
    }

    public float getFieldOfView() {
        return fieldOfView;
    }

    public void setWidth(float width) {

        this.width = width;

        updateProjection();
    }

    public void setHeight(float height) {

        this.height = height;

        updateProjection();
    }

    public void setNear(float near) {

        this.near = near;

        updateProjection();
    }

    public void setFar(float far) {

        this.far = far;

        updateProjection();
    }

    public void setFieldOfView(float fieldOfView) {

        this.fieldOfView = fieldOfView;

        updateProjection();
    }

    @Override
    public void addListener(ShaderProgram shader) {

        projectionUniform.addListener(shader);
        viewUniform.addListener(shader);
    }

    @Override
    public void removeListener(ShaderProgram shader) {

        projectionUniform.removeListener(shader);
        viewUniform.removeListener(shader);
    }

    private void updateProjection() {

        float aspectRatio = width / height;

        float yScale = (float) (1 / Math.tan(Math.toRadians(fieldOfView / 2)));
        float xScale = yScale / aspectRatio;
        float frustumLength = far - near;

        projection.data[Matrix4.M00] = xScale;
        projection.data[Matrix4.M11] = yScale;
        projection.data[Matrix4.M22] = -((far + near) / frustumLength);
        projection.data[Matrix4.M23] = -((2 * near * far) / frustumLength);
        projection.data[Matrix4.M32] = -1;
        projection.data[Matrix4.M33] = 0;

        projectionUniform.setUniformData(projection.data);
    }

    private void updateView() {

        view.set(transform.getScale());

        Matrix4.multiply(view, transform.getOrientation().computeMatrix(), view);
        Matrix4.multiply(view, transform.getPosition(), view);

        viewUniform.setUniformData(view.data);
    }
}
