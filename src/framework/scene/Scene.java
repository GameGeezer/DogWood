package framework.scene;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William Gervasio
 */
public class Scene  {

    private List<Entity> entities = new ArrayList<Entity>();

    public Scene() {

    }

    public void update(int delta) {
        for(Entity entity : entities) {
            entity.update(delta);
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
