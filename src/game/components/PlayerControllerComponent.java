package game.components;

import framework.input.Keyboard;
import framework.scene.Entity;
import framework.scene.components.IDynamicComponent;
import framework.scene.components.IKeyboardComponent;

import java.util.List;

/**
 * Created by Will on 12/18/2014.
 */
public class PlayerControllerComponent extends Entity.EntityComponent implements IDynamicComponent, IKeyboardComponent {

    private boolean moveLeft = false;
    private boolean moveRight = false;

    public PlayerControllerComponent() {

    }

    @Override
    public void update(int delta) {

        List<Entity.EntityComponent> transformComponents = getParent().getComponentsOfType(TransformComponent.class);

        if(transformComponents.size() == 0)
            return;

        TransformComponent transformComponent = (TransformComponent) transformComponents.get(0);

        if(moveLeft)
            transformComponent.translate(-0.05f, 0 , 0);
        if(moveRight)
            transformComponent.translate(0.05f, 0 , 0);
    }

    @Override
    public void onKeyUp(int keyCode) {
        if(Keyboard.KEY_A == keyCode) {
            moveLeft = false;
            if (moveRight) {
                flipAllSpritesX(true);
            }
        }
        if(Keyboard.KEY_D == keyCode) {
            moveRight = false;
            if(moveLeft) {
                flipAllSpritesX(false);
            }
        }
    }

    @Override
    public void onKeyDown(int keyCode) {
        if(Keyboard.KEY_A == keyCode) {
            moveLeft = true;
            if(!moveRight) {
                flipAllSpritesX(false);
            }
        }
        if(Keyboard.KEY_D == keyCode) {
            moveRight = true;
            if(!moveLeft) {
                flipAllSpritesX(true);
            }
        }
    }

    @Override
    public void onKeyRepeat(int keyCode) {

    }

    private void flipAllSpritesX(boolean flipped) {

        for(Entity.EntityComponent component :  getParent().getComponentsOfType(SpriteRenderComponent.class)) {
            SpriteRenderComponent spriteComponent = (SpriteRenderComponent) component;
            spriteComponent.setFlippedX(flipped);
        }
    }
}
