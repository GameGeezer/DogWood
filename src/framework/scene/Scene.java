package framework.scene;

import framework.graphics.Camera;
import framework.graphics.Renderer;
import framework.scene.components.RenderComponent;
import framework.scene.components.UpdateComponent;
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

            List<UpdateComponent> updateComponents = (List<UpdateComponent>)(List<?>) entity.getComponentsOfType(UpdateComponent.class);
            updateComponents.forEach((component) -> component.update(delta));

            List<RenderComponent> renderComponents = (List<RenderComponent>)(List<?>) entity.getComponentsOfType(RenderComponent.class);
            renderComponents.forEach((component) -> component.render(delta));
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
