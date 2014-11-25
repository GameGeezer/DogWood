package game;

import framework.Screen;
import framework.scene.Entity;
import framework.scene.components.EntityComponent;
import framework.scene.components.PositionComponent;

/**
 * Created by Will on 11/24/2014.
 */
public class GameScreen implements Screen {

    public void onPause() {

    }

    public void onResume() {

    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {
        Entity entity = new Entity();
        EntityComponent positionComponent = new PositionComponent();
        entity.addComponent(positionComponent);
        System.out.println(entity.hasComponent(positionComponent));
    }
}
