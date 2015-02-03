package game.components.player.states;

import framework.scene.components.collision.PhysicsBodyComponent;
import framework.util.RangeUtil;

/**
 * Created by Will on 2/2/2015.
 */
public class WalkMovementState extends MovementState {

    private float force;
    private float maxVelocity;

    public WalkMovementState(float force, float maxVelocity) {

        this.force = force;
        this.maxVelocity = maxVelocity;
    }
    protected void onLeaveTop() {


    }

    protected void onBecomeTop() {


    }

    @Override
    public void move(PhysicsBodyComponent body, float x, float y) {

        body.applyForceToCenter(x * force, y * force);

        float velX = RangeUtil.forceIntoRange(body.getLinearVelocityX(), -maxVelocity, maxVelocity);

        float velY = RangeUtil.forceIntoRange(body.getLinearVelocityY(), -maxVelocity, maxVelocity);

        body.setLinearVelocity(velX, velY);
    }
}
