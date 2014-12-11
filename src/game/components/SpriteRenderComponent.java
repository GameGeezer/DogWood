package game.components;

import framework.graphics.Camera;
import framework.graphics.Sprite;
import framework.scene.Entity;
import framework.scene.components.IUpdateEntityComponent;

/**
 * @author William Gervasio
 */
public final class SpriteRenderComponent extends IUpdateEntityComponent {

    private final Sprite sprite;
    private final Camera camera;

    public SpriteRenderComponent(Entity parent, Sprite sprite, Camera camera) {
        super(parent);

        this.sprite = sprite;
        this.camera = camera;
    }

    public void update(int delta) {
        sprite.draw();
    }
}
