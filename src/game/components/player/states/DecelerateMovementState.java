package game.components.player.states;

import framework.scene.components.collision.PhysicsBodyComponent;

/**
 * Created by Will on 2/2/2015.
 */
public class DecelerateMovementState extends MovementState {

    private final PhysicsBodyComponent body;
    private final float dampening;

    public DecelerateMovementState(PhysicsBodyComponent body, float dampening) {

        this.body = body;
        this.dampening = dampening;
    }

    protected void onLeaveTop() {

        body.setLinearDampening(0f);
    }

    protected void onBecomeTop() {

        body.setLinearDampening(dampening);
    }

    @Override
    public void move(PhysicsBodyComponent body, float x, float y) {

    }
}
