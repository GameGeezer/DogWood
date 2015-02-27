package game.components.player;

import framework.scene.Entity;
import framework.scene.StateStack;
import framework.scene.components.TransformComponent;
import framework.scene.components.UpdateComponent;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.scene.components.graphics.SpriteComponent;
import framework.util.math.Vector2f;
import framework.window.Application;
import framework.window.keyboardcallbacks.KeyboardListener;
import game.SpriteAnimation;
import game.states.DecelerateMovementState;
import game.states.MovementState;
import game.states.TimedMovementState;
import game.states.WalkMovementState;

import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

/**
 * @author William Gervasio
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

    private SpriteAnimation walkLeftAnimation = new SpriteAnimation(600, 3, 4, 5);
    private SpriteAnimation walkRightAnimation = new SpriteAnimation(600, 6, 7, 8);
    private SpriteAnimation walkUpAnimation = new SpriteAnimation(600, 9, 10, 11);
    private SpriteAnimation walkDownAnimation = new SpriteAnimation(600, 0, 1, 2);

    private SpriteAnimation idleLeftAnimation = new SpriteAnimation(750, 3);
    private SpriteAnimation idleRightAnimation = new SpriteAnimation(750, 6);
    private SpriteAnimation idleUpAnimation = new SpriteAnimation(750, 9);
    private SpriteAnimation idleDownAnimation = new SpriteAnimation(750, 0);

    private SpriteAnimation currentAnimation;

    private LinkedList<Vector2f> faceDirectionList = new LinkedList();
    private Vector2f faceDirection = null;

    private int animationStartTime;

    public PlayerUpdateComponent() {
        currentAnimation = walkDownAnimation;
        animationStartTime = (int) System.currentTimeMillis();
        faceDirection = Vector2f.DOWN;
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
        setAnimation();

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
                removeFaceDirection(Vector2f.LEFT);

                break;

            case GLFW_KEY_D:
                horizontalMovement -= 1f;
                removeFaceDirection(Vector2f.RIGHT);

                break;

            case GLFW_KEY_W:
                verticalMovement -= 1f;
                removeFaceDirection(Vector2f.UP);

                break;

            case GLFW_KEY_S:
                verticalMovement += 1f;
                removeFaceDirection(Vector2f.DOWN);

                break;

        }

        walkInDirection(horizontalMovement, verticalMovement);
    }

    @Override
    public void onKeyDown ( final int keyCode ) {

        switch (keyCode) {

            case GLFW_KEY_A:
                horizontalMovement -= 1f;
                setFaceDirection(Vector2f.LEFT);

                break;

            case GLFW_KEY_D:
                horizontalMovement += 1f;
                setFaceDirection(Vector2f.RIGHT);

                break;

            case GLFW_KEY_W:
                verticalMovement += 1f;
                setFaceDirection(Vector2f.UP);

                break;

            case GLFW_KEY_S:
                verticalMovement -= 1f;
                setFaceDirection(Vector2f.DOWN);

                break;
        }

        walkInDirection(horizontalMovement, verticalMovement);
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

    private void setAnimation() {

        if(movementStack.peek().equals(decelerationState)) {

            if(faceDirection == Vector2f.UP){
                currentAnimation = idleUpAnimation;
            } else if(faceDirection == Vector2f.DOWN) {
                currentAnimation = idleDownAnimation;
            } else if (faceDirection == Vector2f.LEFT) {
                currentAnimation = idleLeftAnimation;
            } else if (faceDirection == Vector2f.RIGHT) {
                currentAnimation = idleRightAnimation;
            }
        } else if (movementStack.peek().equals(walkState)) {

            if(faceDirection == Vector2f.UP){
                currentAnimation = walkUpAnimation;
            } else if(faceDirection == Vector2f.DOWN) {
                currentAnimation = walkDownAnimation;
            } else if (faceDirection == Vector2f.LEFT) {
                currentAnimation = walkLeftAnimation;
            }else if (faceDirection == Vector2f.RIGHT) {
                currentAnimation = walkRightAnimation;
            }
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
        rollState = new TimedMovementState(bodyComponent, 20000f, 23f, 350f);
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

    private void removeFaceDirection(Vector2f direction) {

        faceDirectionList.remove(direction);

        if(faceDirectionList.size() > 0) {
            if(!(horizontalMovement == 0 && (direction.equals(Vector2f.UP) ||direction.equals(Vector2f.DOWN) ))) {
                faceDirection = faceDirectionList.peek();
            }
        }
    }

    private void setFaceDirection(Vector2f direction) {

        faceDirectionList.addLast(direction);

        if(horizontalMovement ==0) {

            if(verticalMovement != 0) {
                if(verticalMovement == 1) {
                    faceDirectionList.remove(Vector2f.UP);
                    faceDirectionList.push(Vector2f.UP);
                } else {
                    faceDirectionList.remove(Vector2f.DOWN);
                    faceDirectionList.push(Vector2f.DOWN);
                }
            }
        }


        faceDirection = faceDirectionList.peek();


        setAnimation();
    }
}
