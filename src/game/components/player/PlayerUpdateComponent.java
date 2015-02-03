package game.components.player;

import framework.scene.Entity;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.scene.components.util.UpdateComponent;
import framework.util.RangeUtil;
import game.components.player.states.DecelerateMovementState;
import game.components.player.states.MovementState;
import game.components.player.states.StateStack;
import game.components.player.states.WalkMovementState;

import java.util.List;

/**
 * Created by Will on 12/24/2014.
 */
public class PlayerUpdateComponent extends UpdateComponent {

    private List<PlayerControllerComponent> controllerComponents;
    private PhysicsBodyComponent bodyComponent;

    private MovementState decelerationState;
    private MovementState walkState;
    private StateStack<MovementState> movementStack = new StateStack<>();

    private float force = 5000f;

    private float maxVelocity = 20f;

    @Override
    protected void onAttach() {

        controllerComponents = (List<PlayerControllerComponent>) (List<?>) getParent().getComponentsOfType(PlayerControllerComponent.class);

        if(getParent().hasComponentOfType(PhysicsBodyComponent.class)) {
            PhysicsBodyComponent bodyComponent = ((List<PhysicsBodyComponent>) (List<?>) getParent().getComponentsOfType(PhysicsBodyComponent.class)).get(0);

            setBody(bodyComponent);
        }
    }

    @Override
    protected void onDetach() {

        removeBody();
    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

        if(component instanceof PhysicsBodyComponent && bodyComponent == null) {

            setBody((PhysicsBodyComponent) component);
        }
    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

        if(bodyComponent != null && component.equals(bodyComponent)) {

            removeBody();
        }
    }

    @Override
    public void update(int delta) {

        PlayerControllerComponent controllerComponent = controllerComponents.get(0);

        if((Math.abs(controllerComponent.getHorizontalMovement()) > 0 || Math.abs(controllerComponent.getVerticalMovement()) > 0) && movementStack.peek().equals(decelerationState)) {

            movementStack.push(walkState);
        } else if ((controllerComponent.getHorizontalMovement() == 0 && controllerComponent.getVerticalMovement() == 0) && movementStack.peek().equals(walkState)) {

            movementStack.pop();
        }

        movementStack.peek().move(bodyComponent, controllerComponent.getHorizontalMovement(), controllerComponent.getVerticalMovement());
    }

    private void setBody(PhysicsBodyComponent body) {
        bodyComponent = body;
        decelerationState = new DecelerateMovementState(body, 200f);
        walkState = new WalkMovementState(2000f, 18f);
        movementStack.push(decelerationState);
    }

    private void removeBody() {
        bodyComponent = null;
        decelerationState = null;
        walkState = null;
        movementStack.clear();
    }
}
