package framework.scene.components;

import framework.graphics.Camera;
import framework.graphics.Sprite;
import framework.util.math.Vector2;

/**
 * @author William Gervasio
 */
public final class SpriteRenderComponent implements IDynamicEntityComponent {

    private final TransformComponent transformComponent;
    private final Sprite sprite;
    private final Camera camera;

    public SpriteRenderComponent(Sprite sprite, TransformComponent transformComponent, Camera camera) {
        this.transformComponent = transformComponent;
        this.sprite = sprite;
        this.camera = camera;
    }

    public void update(int delta) {
        sprite.draw(camera, transformComponent.getModel());
    }
}
