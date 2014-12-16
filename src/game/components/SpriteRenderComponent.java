package game.components;

import framework.graphics.Camera;
import framework.scene.components.DynamicEntityComponent;
import game.Sprite;
import framework.scene.Entity;
import framework.util.exceptions.RequiredComponentsException;

/**
 * @author William Gervasio
 */
public final class SpriteRenderComponent extends DynamicEntityComponent {

    private final Sprite sprite;
    private final Camera camera;


    public SpriteRenderComponent(Entity parent, Sprite sprite, Camera camera)  throws RequiredComponentsException {
        super(parent);

        this.sprite = sprite;
        this.camera = camera;
    }

    public void update(int delta) {
        sprite.draw();
    }

}
