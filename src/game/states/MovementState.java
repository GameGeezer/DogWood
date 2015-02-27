package game.states;

import framework.scene.StateStack;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.util.math.Vector2f;

/**
 * Created by Will on 2/2/2015.
 */
public abstract class MovementState extends StateStack.State{

    private PhysicsBodyComponent bodyComponent;
    private Vector2f moveDirection = new Vector2f();

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

    public Vector2f getMoveDirection() {

        return moveDirection;
    }
}
