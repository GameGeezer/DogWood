package game;

/**
 * Created by Will on 12/30/2014.
 */

import framework.scene.Entity;
import framework.scene.components.RenderComponent;
import framework.scene.components.UpdateComponent;
import framework.scene.uniforms.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William Gervasio
 */
public class Scene {

    private static List<Entity> entities = new ArrayList<Entity>();
    private static Renderer renderer = new Renderer();
    private static Camera camera = new Camera(800, 600, 0.1f, 100, 60);

    public static void update(int delta) {

        renderer.clearScreen();

        for (Entity entity : entities) {

            List<UpdateComponent> updateComponents = (List<UpdateComponent>) (List<?>) entity.getComponentsOfType(UpdateComponent.class);
            updateComponents.forEach((component) -> component.update(delta));
        }
    }

    public static void render(int delta) {

        for (Entity entity : entities) {

            List<RenderComponent> renderComponents = (List<RenderComponent>) (List<?>) entity.getComponentsOfType(RenderComponent.class);
            renderComponents.forEach((component) -> component.render(delta));
        }
    }

    public static void addEntity(Entity entity) {

        entities.add(entity);
    }

    public static void removeEntity(Entity entity) {

        entities.remove(entity);
    }

    public static Camera getCamera() {

        return camera;
    }
}