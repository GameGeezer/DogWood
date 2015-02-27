package framework.scene.components.general;

import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.uniform.IUniformWrapper;
import framework.scene.Camera;
import framework.scene.Entity;
import framework.util.exceptions.EntityException;

/**
 * @author William Gervasio
 */
public class CameraReferenceComponent extends Entity.EntityComponent implements IUniformWrapper {

    private final Camera camera;

    public CameraReferenceComponent(final Camera camera) {

        this.camera = camera;
    }

    @Override
    protected void onAttach() throws EntityException {

        if (getParent().getComponentsOfType(CameraReferenceComponent.class).size() > 1) {

            removeSelfFromParent();

            throw new EntityException("Only one CameraReferenceComponent may be attached to an entity");
        }
    }

    @Override
    protected void onDetach() {

    }

    @Override
    protected void onComponentAttachedToParent(final Entity.EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(final Entity.EntityComponent component) {

    }

    @Override
    public void addListener(final ShaderProgram shader) {

        camera.addListener(shader);
    }

    @Override
    public void removeListener(final ShaderProgram shader) {

        camera.removeListener(shader);
    }
}
