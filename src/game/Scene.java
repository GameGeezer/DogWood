package game;

/**
 * Created by Will on 12/30/2014.
 */

import framework.scene.Entity;
import framework.scene.components.RenderComponent;
import framework.scene.components.UpdateComponent;

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

            List<UpdateComponent> updateComponents = (List<UpdateComponent>)(List<?>) entity.getComponentsOfType(UpdateComponent.class);
            updateComponents.forEach((component) -> component.update(delta));
        }
    }

    public void render(int delta) {

        for(Entity entity : entities) {

            List<RenderComponent> renderComponents = (List<RenderComponent>)(List<?>) entity.getComponentsOfType(RenderComponent.class);
            renderComponents.forEach((component) -> component.render(delta));
        }
    }

    public void addEntity(Entity entity) {

        entities.add(entity);
    }
}