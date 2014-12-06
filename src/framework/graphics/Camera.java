package framework.graphics;

import framework.scene.Entity;
import framework.scene.Transform;
import framework.util.math.Matrix4;
import framework.util.math.Vector2;
import framework.util.math.Vector3;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Will on 2/7/14.
 */
public class Camera extends Entity {

    private Matrix4 view, projection;
    private Vector2 translation;
    private float width, height, near, far;

    public Camera(float width, float height, float near, float far) {
        view = new Matrix4();
        projection = new Matrix4();
        this.width = width;
        this.height = height;
        this.near = near;
        this.far = far;
    }

    public void lookAt(Vector3 point) {

    }

    public Camera setTranslation(Vector2 translation) {
        this.translation = translation;

        return this;
    }

    public Camera setTranslation(float x, float y) {
        translation = new Vector2(x, y);

        return this;
    }

    public Camera translate(Vector2 translation) {
        this.translation.add(translation);

        return this;
    }

    public Camera translate(float x, float y) {
        translation.add(x, y);

        return this;
    }

    public Matrix4 getView() {
        return view;
    }

    public Matrix4 getProjection() {
        return projection;
    }
}
