package game.components.player;

import framework.scene.Entity;
import framework.scene.components.TransformComponent;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.scene.components.UpdateComponent;
import framework.util.math.Vector2;
import framework.window.Application;
import framework.window.keyboardcallbakcs.KeyboardListener;
import game.Scene;
import game.SpriteAnimation;
import game.components.SpriteComponent;
import game.components.player.states.DecelerateMovementState;
import game.components.player.states.MovementState;
import framework.scene.StateStack;
import game.components.player.states.RollMovementState;
import game.components.player.states.WalkMovementState;
import game.weapons.BasicBullet;

import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

/**
 * Created by Will on 12/24/2014.
 */
public class PlayerUpdateComponent extends UpdateComponent implements KeyboardListener{

    private PhysicsBodyComponent bodyComponent;
    private TransformComponent transformComponent;
    private SpriteComponent spriteComponent;

    private StateStack<MovementState> movementStack = new StateStack<>();
    private MovementState decelerationState;
    private MovementState walkState;
    private MovementState rollState;


    private float horizontalMovement = 0f;
    private float verticalMovement = 0f;


    private SpriteAnimation walkLeftAnimation = new SpriteAnimation(750, 3, 4, 5);
    private SpriteAnimation walkRightAnimation = new SpriteAnimation(750, 6, 7, 8);
    private SpriteAnimation walkUpAnimation = new SpriteAnimation(750, 9, 10, 11);
    private SpriteAnimation walkDownAnimation = new SpriteAnimation(750, 0, 1, 2);

    private SpriteAnimation idleLeftAnimation = new SpriteAnimation(750, 3);
    private SpriteAnimation idleRightAnimation = new SpriteAnimation(750, 6);
    private SpriteAnimation idleUpAnimation = new SpriteAnimation(750, 9);
    private SpriteAnimation idleDownAnimation = new SpriteAnimation(750, 0);

    private SpriteAnimation currentAnimation;

    private Vector2 faceDirection = Vector2.DOWN;

    private int animationStartTime;

    public PlayerUpdateComponent() {
        currentAnimation = walkDownAnimation;
        animationStartTime = (int) System.currentTimeMillis();

    }

    @Override
    protected void onAttach() {

        Application.KEYBOARD.addListener(this);

        transformComponent = (TransformComponent) getParent().getFirstComponentOfType(TransformComponent.class);
        spriteComponent =  (SpriteComponent) getParent().getFirstComponentOfType(SpriteComponent.class);

        if(getParent().hasComponentOfType(PhysicsBodyComponent.class)) {
            PhysicsBodyComponent bodyComponent = ((List<PhysicsBodyComponent>) (List<?>) getParent().getComponentsOfType(PhysicsBodyComponent.class)).get(0);

            setBody(bodyComponent);
        }
    }

    @Override
    protected void onDetach() {

        Application.KEYBOARD.removeListener(this);

        transformComponent = null;
        spriteComponent =  null;

        removeBody();
    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

        if(component instanceof TransformComponent && transformComponent == null) {

            transformComponent = (TransformComponent) component;
        }

        if(component instanceof SpriteComponent && spriteComponent == null) {

            spriteComponent = (SpriteComponent) component;
        }


        if(component instanceof PhysicsBodyComponent && bodyComponent == null) {

            setBody((PhysicsBodyComponent) component);
        }
    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

        if(transformComponent != null && component.equals(transformComponent)) {

            transformComponent = null;
        }

        if(spriteComponent != null && component.equals(spriteComponent)) {

            spriteComponent = null;
        }

        if(bodyComponent != null && component.equals(bodyComponent)) {

            removeBody();
        }
    }

    @Override
    public void update(int delta) {

        if((Math.abs(horizontalMovement) > 0 || Math.abs(verticalMovement) > 0) && movementStack.peek().equals(decelerationState)) {


        } else if ((horizontalMovement == 0 && verticalMovement == 0) && movementStack.peek().equals(walkState)) {

            movementStack.pop();
        }

        movementStack.peek().move();

        int xIndex = currentAnimation.getFrameIndex(animationStartTime) % spriteComponent.getCellsWide();
        int yIndex = currentAnimation.getFrameIndex(animationStartTime) / spriteComponent.getCellsWide();
        spriteComponent.setFrame(xIndex, yIndex);
    }



    @Override
    public void onKeyUp ( final int keyCode ) {

        switch ( keyCode ) {

            case GLFW_KEY_A:
                horizontalMovement += 1f;
                break;

            case GLFW_KEY_D:
                horizontalMovement -= 1f;
                break;

            case GLFW_KEY_W:
                verticalMovement -= 1f;
                break;

            case GLFW_KEY_S:
                verticalMovement += 1f;
                break;

        }

        walkInDirection(horizontalMovement, verticalMovement);
        setAnimation();
    }

    @Override
    public void onKeyDown ( final int keyCode ) {

        switch (keyCode) {

            case GLFW_KEY_A:

                faceDirection = Vector2.LEFT;
                horizontalMovement -= 1f;
                animationStartTime = (int) System.currentTimeMillis();
                break;

            case GLFW_KEY_D:

                faceDirection = Vector2.RIGHT;
                horizontalMovement += 1f;
                animationStartTime = (int) System.currentTimeMillis();
                break;

            case GLFW_KEY_W:
                verticalMovement += 1f;
                faceDirection = Vector2.UP;
                animationStartTime = (int) System.currentTimeMillis();
                break;

            case GLFW_KEY_S:
                faceDirection = Vector2.DOWN;
                verticalMovement -= 1f;
                animationStartTime = (int) System.currentTimeMillis();
                break;

            case GLFW_KEY_SPACE:
                fireBullet();
                break;
        }

        walkInDirection(horizontalMovement, verticalMovement);
        setAnimation();
    }


    @Override
    public void onKeyDoublePressed ( final int keyCode ) {

        switch (keyCode) {

            case GLFW_KEY_A:
                rollInDirection(-1f, 0f);
                break;
            case GLFW_KEY_D:
                rollInDirection(1f, 0f);
                break;
            case GLFW_KEY_W:
                rollInDirection(0f, 1f);
                break;
            case GLFW_KEY_S:
                rollInDirection(0f, -1f);
                break;
        }
    }

    private void fireBullet() {

        Scene.addEntity(new BasicBullet(transformComponent.getX() + (0.3f * faceDirection.getX()), transformComponent.getY() + (0.3f * faceDirection.getY()), transformComponent.getZ(), faceDirection));
    }

    private void setAnimation() {

        if (verticalMovement > 0) {
            currentAnimation = walkUpAnimation;
        } else if (verticalMovement < 0) {
            currentAnimation = walkDownAnimation;
        }

        if (horizontalMovement > 0) {
            currentAnimation = walkRightAnimation;
        } else if (horizontalMovement < 0) {
            currentAnimation = walkLeftAnimation;
        }
    }

    /**
     * TODO delete
     * @param body
     */
    private void setBody(PhysicsBodyComponent body) {

        bodyComponent = body;
        decelerationState = new DecelerateMovementState(bodyComponent, 200f);
        walkState = new WalkMovementState(bodyComponent, 2000f, 18f);
        rollState = new RollMovementState(bodyComponent, 20000f, 23f, 350f);
        movementStack.push(decelerationState);
    }

    /**
     * TODO delete
     */
    private void removeBody() {

        bodyComponent = null;
        decelerationState = null;
        walkState = null;
        rollState = null;
        movementStack.clear();
    }

    private void rollInDirection(float x, float y) {

        if(movementStack.peek().equals(decelerationState) || movementStack.peek().equals(walkState)) {

            rollState.setDirection(x, y);
            movementStack.push(rollState);
        }
    }

    private void walkInDirection(float x, float y) {

        walkState.setDirection(x, y);

        if(movementStack.peek().equals(decelerationState)) {

            movementStack.push(walkState);
        }
    }
}
