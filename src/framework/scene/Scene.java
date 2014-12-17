package framework.scene;

import framework.graphics.Camera;
import framework.graphics.Renderer;
import game.UniformCamera;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William Gervasio
 */
public class Scene  {

    private List<Entity> entities = new ArrayList<Entity>();
    private Renderer renderer = new Renderer();

    public Scene() {

    }

    public void update(int delta) {
        renderer.clearScreen();
        for(Entity entity : entities) {
            entity.update(delta);
            entity.render(delta);
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
