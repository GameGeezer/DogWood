package framework.scene.components;

import framework.graphics.Sprite;
import framework.util.math.Vector3;

/**
 * @author William Gervasio
 */
public final class SpriteRenderComponent implements DynamicEntityComponent {

    private final Vector3 position, scale;
    private final Sprite sprite;

    public SpriteRenderComponent(Vector3 position, Vector3 scale, Sprite sprite) {
        this.position = position;
        this.scale = scale;
        this.sprite = sprite;
    }

    public void update(int delta) {

    }
}
