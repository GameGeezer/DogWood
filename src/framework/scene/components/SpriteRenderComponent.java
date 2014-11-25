package framework.scene.components;

import framework.graphics.Camera;
import framework.graphics.Sprite;
import framework.util.math.Vector3;

/**
 * @author William Gervasio
 */
public final class SpriteRenderComponent implements DynamicEntityComponent {

    private final Vector3 position, scale, dimensions;
    private final Sprite sprite;
    private final Camera camera;

    public SpriteRenderComponent(Vector3 position, Vector3 scale, Vector3 dimensions, Sprite sprite, Camera camera) {
        this.position = position;
        this.scale = scale;
        this.dimensions = dimensions;
        this.sprite = sprite;
        this.camera = camera;
    }

    public void update(int delta) {

    }
}
