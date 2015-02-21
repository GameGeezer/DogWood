package game.components.player.states;

import framework.scene.components.collision.PhysicsBodyComponent;

/**
 * Created by Will on 2/2/2015.
 */
public class DecelerateMovementState extends MovementState {

    private final float dampening;

    public DecelerateMovementState(PhysicsBodyComponent bodyComponent, float dampening) {
        super(bodyComponent);

        this.dampening = dampening;
    }

    protected void onLeaveTop() {

        getBodyComponent().setLinearDampening(0f);
    }

    protected void onBecomeTop() {

        getBodyComponent().setLinearDampening(dampening);
    }

    @Override
    public void move() {

    }
}
