package game;

import framework.Screen;
import framework.graphics.Sprite;
import framework.scene.Entity;
import framework.scene.components.EntityComponent;
import framework.scene.components.PositionComponent;
import framework.scene.components.ScaleComponent;
import framework.scene.components.SpriteRenderComponent;

/**
 * @author William Gervasio
 */
public class GameScreen implements Screen {

    public void onPause() {

    }

    public void onResume() {
        Entity entity = new Entity();
        PositionComponent positionComponent= new PositionComponent();
        ScaleComponent scaleComponent = new ScaleComponent();
        Sprite sprite = new Sprite();
        EntityComponent renderComponent = new SpriteRenderComponent(positionComponent, scaleComponent, sprite);
        entity.addComponent(positionComponent);
        entity.addComponent(scaleComponent);
        entity.addComponent(renderComponent);
    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {

    }
}
