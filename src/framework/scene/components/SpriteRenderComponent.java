package framework.scene.components;

import framework.graphics.Camera;
import framework.graphics.Sprite;
import framework.util.math.Vector3;

/**
 * @author William Gervasio
 */
public final class SpriteRenderComponent implements RenderEntityComponent {

    private final Vector3 position, scale, dimensions;
    private final Sprite sprite;

    public SpriteRenderComponent(Vector3 position, Vector3 scale, Vector3 dimensions, Sprite sprite) {
        this.position = position;
        this.scale = scale;
        this.dimensions = dimensions;
        this.sprite = sprite;
    }

    public void render(int delta, Camera camera) {

    }
}
