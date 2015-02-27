package game.components.player;

import framework.scene.Entity;
import framework.scene.StateStack;
import framework.scene.components.TransformComponent;
import framework.scene.components.UpdateComponent;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.util.exceptions.EntityException;
import framework.util.math.Vector2f;
import game.states.DecelerateMovementState;
import game.states.MovementState;
import game.states.WalkMovementState;

import java.util.List;

/**
 * Created by Will on 2/26/2015.
 */
public class DogUpdateComponent extends UpdateComponent {

    private final float MASTER_IN_RANGE = 0.3f;

    private StateStack<MovementState> movementStack = new StateStack();
    private MovementState decelerationState;
    private MovementState walkState;

    private PhysicsBodyComponent bodyComponent;
    private TransformComponent transformComponent;

    private Entity master;

    private Vector2f dummyVector = new Vector2f();

    public DogUpdateComponent(Entity master) {

        this.master = master;
    }

    @Override
    public void update(int delta) {
        TransformComponent masterTransform = (TransformComponent) master.getFirstComponentOfType(TransformComponent.class);

        Vector2f direction = dummyVector.set(masterTransform.getX(), masterTransform.getY()).sub(transformComponent.getX(), transformComponent.getY()).normalize();
        walkState.setDirection(direction.x, direction.y);

        dummyVector.set(masterTransform.getX(), masterTransform.getY()).sub(transformComponent.getX(), transformComponent.getY());
        if(movementStack.peek().equals(decelerationState)) {
            movementStack.push(walkState);
        }

        if(dummyVector.length() >= MASTER_IN_RANGE) {
            movementStack.peek().move();
        }
    }

    @Override
    protected void onAttach() throws EntityException {

        transformComponent = (TransformComponent) getParent().getFirstComponentOfType(TransformComponent.class);

        if(getParent().hasComponentOfType(PhysicsBodyComponent.class)) {
            PhysicsBodyComponent bodydComponent = ((List<PhysicsBodyComponent>) (List<?>) getParent().getComponentsOfType(PhysicsBodyComponent.class)).get(0);

            setBody(bodydComponent);
        }
    }

    @Override
    protected void onDetach() {

    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

        if(component instanceof PhysicsBodyComponent && bodyComponent == null) {

            setBody((PhysicsBodyComponent) component);
        }

        if(component instanceof TransformComponent && transformComponent == null) {

            transformComponent = (TransformComponent) component;
        }
    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

    }

    private void setBody(PhysicsBodyComponent body) {

        bodyComponent = body;
        decelerationState = new DecelerateMovementState(bodyComponent, 200f);
        walkState = new WalkMovementState(bodyComponent, 1000f, 14f);
        movementStack.push(decelerationState);
    }

    /**
     * TODO delete
     */
    private void removeBody() {

        bodyComponent = null;
        decelerationState = null;
        walkState = null;
        movementStack.clear();
    }
}
