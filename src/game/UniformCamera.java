package game;

import framework.graphics.Camera;
import framework.graphics.opengl.ShaderProgram;
import framework.graphics.uniform.MatrixUniform;
import framework.util.math.Matrix4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 12/14/2014.
 */
public class UniformCamera {

    public static final String PROJECTION_UNIFORM = "u_projectionMatrix";
    public static final String VIEW_UNIFORM = "u_viewMatrix";

    private MatrixUniform projectionUniform = new MatrixUniform(PROJECTION_UNIFORM, MatrixUniform.MatrixUniformType.MATRIX4);
    private MatrixUniform viewUniform = new MatrixUniform(VIEW_UNIFORM, MatrixUniform.MatrixUniformType.MATRIX4);

    private Camera camera;

    public UniformCamera(float width, float height, float near, float far, float fieldOfView) {
        camera = new Camera(width, height, near, far, fieldOfView);
        projectionUniform.setUniformData(camera.getProjection().data);
        viewUniform.setUniformData(camera.getView().data);
    }

    public Matrix4 getView() {
        return camera.getView();
    }

    public Matrix4 getProjection() {
        return camera.getProjection();
    }

    public float getWidth() {
        return camera.getWidth();
    }

    public float getHeight() {
        return camera.getHeight();
    }

    public float getNear() {
        return camera.getNear();
    }

    public float getFar() {
        return camera.getFar();
    }

    public float getFieldOfView() {
        return  camera.getFieldOfView();
    }

    public void setWidth(float width) {
        camera.setWidth(width);
        updateProjectionUniform();
    }

    public void setHeight(float height) {
        camera.setHeight(height);
        updateProjectionUniform();
    }

    public void setNear(float near) {
        camera.setNear(near);
        updateProjectionUniform();
    }

    public void setFar(float far) {
        camera.setFar(far);
        updateProjectionUniform();
    }

    public void setFieldOfView(float fieldOfView) {
        camera.setFieldOfView(fieldOfView);
        updateProjectionUniform();
    }

    public void addListener(ShaderProgram shader) {
        projectionUniform.addListener(shader);
        viewUniform.addListener(shader);
    }

    public void removeListener(ShaderProgram shader) {
        projectionUniform.removeListener(shader);
        viewUniform.removeListener(shader);
    }

    private void updateProjectionUniform() {

        projectionUniform.setUniformData(camera.getProjection().data);
    }

}
