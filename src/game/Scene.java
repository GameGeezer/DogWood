package game;

/**
 * Created by Will on 12/30/2014.
 */

import framework.scene.Entity;
import framework.scene.components.graphics.RenderComponent;
import framework.scene.components.UpdateComponent;
import framework.scene.Camera;
import framework.window.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William Gervasio
 */
public class Scene {

    private static List<Entity> entities = new ArrayList<Entity>();
    private static Camera camera = new Camera(800, 600, 0.1f, 100, 60);

    static {
        camera.rotate((float)Math.PI/ 5f, 0f, 0f);
    }
    public static void update(int delta) {

        Application.PHYSICS_WORLD.step(1f / (float) 1000, 8 , 3);

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