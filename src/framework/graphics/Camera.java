package framework.graphics;

import framework.util.math.Matrix4;
import framework.util.math.Vector3;

/**
 * Created by Will on 2/7/14.
 */
public class Camera {

    protected Matrix4 view = new Matrix4(), projection = new Matrix4();
    protected float width, height, near, far, fieldOfView;

    public Camera(float width, float height, float near, float far, float fieldOfView) {

        this.width = width;
        this.height = height;
        this.near = near;
        this.far = far;
        this.fieldOfView = fieldOfView;

        updateProjectionMatrix();
    }

    /**
     * TODO
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
        return  fieldOfView;
    }

    public void setWidth(float width) {

        this.width = width;
        updateProjectionMatrix();
    }

    public void setHeight(float height) {

        this.height = height;
        updateProjectionMatrix();
    }

    public void setNear(float near) {

        this.near = near;
        updateProjectionMatrix();
    }

    public void setFar(float far) {

        this.far = far;
        updateProjectionMatrix();
    }

    public void setFieldOfView(float fieldOfView) {

        this.fieldOfView = fieldOfView;
        updateProjectionMatrix();
    }

    protected void updateProjectionMatrix() {

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
    }
}
