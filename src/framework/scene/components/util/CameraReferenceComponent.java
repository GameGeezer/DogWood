package framework.scene.components.util;

import framework.scene.Entity;
import framework.scene.Camera;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.components.graphics.UniformComponent;
import framework.util.exceptions.EntityException;

/**
 * Created by Will on 1/4/2015.
 */
public class CameraReferenceComponent extends UniformComponent {

    private Camera camera;

    public CameraReferenceComponent(Camera camera) {

        this.camera = camera;
    }

    @Override
    protected void onAttach() throws EntityException {

        if(getParent().getComponentsOfType(CameraReferenceComponent.class).size() > 1) {

            removeSelfFromParent();

            throw new EntityException("Only one CameraReferenceComponent may be attached to an entity");
        }
    }

    @Override
    protected void onDetach() {

    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

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
