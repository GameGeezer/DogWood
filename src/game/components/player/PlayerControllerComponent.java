package game.components.player;

import framework.input.IKeyboardListener;
import framework.input.Keyboard;
import framework.scene.Entity;
import framework.scene.components.UpdateComponent;
import framework.scene.components.util.TransformComponent;
import game.Scene;
import game.components.DynamicComponent;
import game.weapons.BasicBullet;

import java.util.List;

/**
 * Created by Will on 12/18/2014.
 */

public class PlayerControllerComponent extends Entity.EntityComponent implements IKeyboardListener {

    private List<DynamicComponent> dynamicComponents;

    private boolean moveLeft = false;
    private boolean moveRight = false;

    private boolean moveUp = false;
    private boolean moveDown = false;

    public PlayerControllerComponent() {


    }

    @Override
    protected void onAttach() {

        dynamicComponents = (List<DynamicComponent>) (List<?>) getParent().getComponentsOfType(DynamicComponent.class);

        Keyboard.addListener(this);
    }

    @Override
    protected void onDetach() {

        Keyboard.removeListener(this);
    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

    }


    @Override
    public void onKeyUp(int keyCode) {

        switch(keyCode) {

            case Keyboard.KEY_A:
                moveLeft = false;
                break;

            case Keyboard.KEY_D:
                moveRight = false;
                break;

            case Keyboard.KEY_W:
                moveUp = false;
                break;

            case Keyboard.KEY_S:
                moveDown = false;
                break;
        }
    }

    @Override
    public void onKeyDown(int keyCode) {

        switch(keyCode) {

            case Keyboard.KEY_A:
                moveLeft = true;
                break;

            case Keyboard.KEY_D:
                moveRight = true;
                break;

            case Keyboard.KEY_W:
                moveUp = true;
                break;

            case Keyboard.KEY_S:
                moveDown = true;
                break;

            case Keyboard.KEY_SPACE:
                fireBullet();
                break;
        }
    }

    @Override
    public void onKeyRepeat(int keyCode) {

    }

    public boolean isMoveLeft() {

        return moveLeft;
    }

    public boolean isMoveRight() {

        return moveRight;
    }

    public boolean isMoveUp() {

        return moveUp;
    }

    public boolean isMoveDown() {

        return moveDown;
    }

    private void fireBullet() {

        List<TransformComponent> transformComponents = (List<TransformComponent>) (List<?>) getParent().getComponentsOfType(TransformComponent.class);
        TransformComponent transformComponent = transformComponents.get(0);

        List<DynamicComponent> dynamicComponents = (List<DynamicComponent>) (List<?>) getParent().getComponentsOfType(DynamicComponent.class);
        DynamicComponent dynamicComponent = dynamicComponents.get(0);

        BasicBullet bullet = new BasicBullet(transformComponent, dynamicComponent);
        Scene.addEntity(bullet);
    }
}
