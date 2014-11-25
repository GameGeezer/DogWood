package framework.scene.components;

import framework.graphics.Camera;

/**
 * @author William Gervasio
 */
public interface RenderEntityComponent extends EntityComponent {
    public abstract void render(int delta, Camera camera);
}
