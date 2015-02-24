package game.components.player.states;

import framework.scene.StateStack;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.util.math.Vector2;

/**
 * Created by Will on 2/2/2015.
 */
public abstract class MovementState extends StateStack.State{

    private PhysicsBodyComponent bodyComponent;
    private Vector2 moveDirection = new Vector2();

    public MovementState(PhysicsBodyComponent bodyComponent) {

        this.bodyComponent = bodyComponent;
    }

    public abstract void move();

    protected PhysicsBodyComponent getBodyComponent() {

        return bodyComponent;
    }

    public void setDirection(float x, float y) {

        moveDirection.set(x, y);
        moveDirection.normalize();
    }

    public Vector2 getMoveDirection() {

        return moveDirection;
    }
}
