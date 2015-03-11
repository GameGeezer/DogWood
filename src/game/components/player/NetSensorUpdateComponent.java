package game.components.player;

import framework.scene.Entity;
import framework.scene.components.TransformComponent;
import framework.scene.components.UpdateComponent;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.util.math.Vector2f;
import framework.window.Application;
import framework.window.keyboardcallbacks.KeyboardListener;

/**
 * @author William Gervasio
 */
public class NetSensorUpdateComponent extends UpdateComponent implements KeyboardListener{

    private TransformComponent transformComponent;
    private PhysicsBodyComponent bodyComponent;

    private PlayerUpdateComponent playerUpdateComponent;
    private TransformComponent playerTransformComponent;

    public NetSensorUpdateComponent(Entity player) {

        playerUpdateComponent = (PlayerUpdateComponent) player.getFirstComponentOfType(PlayerUpdateComponent.class);
        playerTransformComponent = (TransformComponent) player.getFirstComponentOfType(TransformComponent.class);
    }

    @Override
    protected void onAttach() {

        Application.KEYBOARD.addListener(this);

        transformComponent = (TransformComponent) getParent().getFirstComponentOfType(TransformComponent.class);

        bodyComponent = (PhysicsBodyComponent) getParent().getFirstComponentOfType(PhysicsBodyComponent.class);;
    }

    @Override
    protected void onDetach() {

        Application.KEYBOARD.removeListener(this);

        transformComponent = null;

        bodyComponent = null;
    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

        if(component instanceof TransformComponent && transformComponent == null) {

            transformComponent = (TransformComponent) component;
        }

        if(component instanceof PhysicsBodyComponent && bodyComponent == null) {

            bodyComponent = (PhysicsBodyComponent) component;
        }
    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

        if(transformComponent != null && component.equals(transformComponent)) {

            transformComponent = null;
        }

        if(bodyComponent != null && component.equals(bodyComponent)) {

            bodyComponent = null;
        }
    }

    @Override
    public void update(int delta) {

        float x = 0;
        float y = 0;

        if(playerUpdateComponent.getFaceDirection().equals(Vector2f.LEFT)) {

            x = playerTransformComponent.getX() + 0.35f * playerUpdateComponent.getFaceDirection().x;
            y = playerTransformComponent.getY() + 0.2f;

        } else  if(playerUpdateComponent.getFaceDirection().equals(Vector2f.RIGHT)) {

            x = playerTransformComponent.getX() + 0.35f * playerUpdateComponent.getFaceDirection().x;
            y = playerTransformComponent.getY() + 0.2f;

        } else  if(playerUpdateComponent.getFaceDirection().equals(Vector2f.UP)) {

            x = playerTransformComponent.getX();
            y = playerTransformComponent.getY() + 0.5f * playerUpdateComponent.getFaceDirection().y;

        } else  if(playerUpdateComponent.getFaceDirection().equals(Vector2f.DOWN)) {

            x = playerTransformComponent.getX();
            y = playerTransformComponent.getY() + 0.2f * playerUpdateComponent.getFaceDirection().y;
        }

        bodyComponent.setPosition(x, y);
    }
}
