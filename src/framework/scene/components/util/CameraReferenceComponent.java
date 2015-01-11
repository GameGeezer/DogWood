package framework.scene.components.util;

import framework.scene.uniforms.Camera;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.components.graphics.UniformComponent;

/**
 * Created by Will on 1/4/2015.
 */
public class CameraReferenceComponent extends UniformComponent {

    private Camera camera;

    public CameraReferenceComponent(Camera camera) {

        this.camera = camera;
    }

    public void addListener(ShaderProgram shader) {

        camera.addListener(shader);
    }

    public void removeListener(ShaderProgram shader) {

        camera.removeListener(shader);
    }
}
