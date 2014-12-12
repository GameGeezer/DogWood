package game.components;

import framework.graphics.Camera;
import framework.graphics.Mesh;
import framework.graphics.Sprite;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.components.IEntityComponent;
import framework.scene.components.IUpdateEntityComponent;
import framework.util.dataTypes.DatatypeUtil;
import framework.util.exceptions.RequiredComponentsException;
import framework.util.math.Matrix4;

import java.nio.FloatBuffer;
import java.util.List;

/**
 * @author William Gervasio
 */
public final class SpriteRenderComponent extends IUpdateEntityComponent {

    private final Sprite sprite;
    private final Camera camera;

    private TransformComponent transformComponent;

    private FloatBuffer projectionViewModelBuffer = FloatBuffer.allocate(DatatypeUtil.FLOAT_SIZE_BYTES * Matrix4.NUMBER_OF_CELLS);
    private Matrix4 projectionViewModelMatrix = new Matrix4();

    public SpriteRenderComponent(Entity parent, Sprite sprite, Camera camera)  throws RequiredComponentsException {
        super(parent);

        this.sprite = sprite;
        this.camera = camera;

        List<IEntityComponent> transformComponents = getComponentsOfType(TransformComponent.class);
        if(transformComponents == null)
            throw new RequiredComponentsException("This component requires a TransformComponent to be added to the entity before it");

        transformComponent = (TransformComponent)transformComponents.get(0);
    }

    public void update(int delta) {
        sprite.draw();
    }

    private FloatBuffer createProjectionViewModelBuffer(Camera camera) {
        projectionViewModelMatrix.set(camera.getProjection());
        Matrix4.multiply(projectionViewModelMatrix, camera.getView(), projectionViewModelMatrix);
        Matrix4.multiply(projectionViewModelMatrix, transformComponent.createModel(), projectionViewModelMatrix);
        projectionViewModelBuffer.reset();
        projectionViewModelBuffer.put(camera.getProjection().data);

        return projectionViewModelBuffer;
    }
}
