package game.components;

import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.components.IUniformComponent;
import game.UniformCamera;

/**
 * Created by Will on 12/16/2014.
 */
public class UniformCameraReferenceComponent extends Entity.EntityComponent implements IUniformComponent{
    private UniformCamera camera;

    public UniformCameraReferenceComponent(UniformCamera camera) {
        this.camera = camera;
    }

    @Override
    public void addListener(ShaderProgram shader) {
        camera.addListener(shader);
    }

    @Override
    public void removeListener(ShaderProgram shader) {
        camera.removeListener(shader);
    }
}
