package game.components.player;

import framework.input.Keyboard;
import framework.scene.components.KeyboardComponent;
import game.SpriteAnimation;
import game.components.SpriteComponent;

/**
 * Created by Will on 12/18/2014.
 */

public class PlayerControllerComponent extends KeyboardComponent {

    private boolean moveLeft = false;
    private boolean moveRight = false;

    SpriteAnimation walkLeftAnimation;
    SpriteAnimation walkRightAnimation;
    SpriteAnimation walkUpAnimation;
    SpriteAnimation walkDownAnimation;

    public PlayerControllerComponent() {

        walkLeftAnimation = new SpriteAnimation();
        walkRightAnimation = new SpriteAnimation();
        walkUpAnimation = new SpriteAnimation();
        walkDownAnimation = new SpriteAnimation();

    }

    @Override
    public void onKeyUp(int keyCode) {
        if(Keyboard.KEY_A == keyCode) {
            moveLeft = false;
            if (moveRight) {
               // flipAllSpritesX(true);
            }
        }
        if(Keyboard.KEY_D == keyCode) {
            moveRight = false;
            if(moveLeft) {
               // flipAllSpritesX(false);
            }
        }
    }

    @Override
    public void onKeyDown(int keyCode) {
        if(Keyboard.KEY_A == keyCode) {
            moveLeft = true;
            if(!moveRight) {
               // flipAllSpritesX(false);
            }
        }
        if(Keyboard.KEY_D == keyCode) {
            moveRight = true;
            if(!moveLeft) {
                //flipAllSpritesX(true);
            }
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
}
