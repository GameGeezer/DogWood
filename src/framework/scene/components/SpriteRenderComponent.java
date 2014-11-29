package framework.scene.components;

import framework.graphics.Camera;
import framework.graphics.Sprite;
import framework.util.math.Vector2;

/**
 * @author William Gervasio
 */
public final class SpriteRenderComponent implements IDynamicEntityComponent {

    private final Vector2 position, scale, dimensions;
    private final Sprite sprite;
    private final Camera camera;

    public SpriteRenderComponent(Vector2 position, Vector2 scale, Vector2 dimensions, Sprite sprite, Camera camera) {
        this.position = position;
        this.scale = scale;
        this.dimensions = dimensions;
        this.sprite = sprite;
        this.camera = camera;
    }

    public void update(int delta) {

    }
}
