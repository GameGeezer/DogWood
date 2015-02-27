package game.states;

import framework.scene.components.collision.PhysicsBodyComponent;
import framework.util.RangeUtil;

/**
 * Created by Will on 2/2/2015.
 */
public class WalkMovementState extends MovementState {

    private float force;
    private float maxVelocity;

    public WalkMovementState(PhysicsBodyComponent bodyComponent, float force, float maxVelocity) {
        super(bodyComponent);

        this.force = force;
        this.maxVelocity = maxVelocity;
    }

    @Override
    protected void onBecomeTop() {

        getBodyComponent().setLinearDampening(100f);
    }

    @Override
    protected void onLeaveTop() {
        getBodyComponent().setLinearDampening(0f);

    }

    @Override
    public void move() {

        getBodyComponent().applyForceToCenter(getMoveDirection().x * force, getMoveDirection().y * force);

        float velX = RangeUtil.forceIntoRange(getBodyComponent().getLinearVelocityX(), -maxVelocity, maxVelocity);
        float velY = RangeUtil.forceIntoRange(getBodyComponent().getLinearVelocityY(), -maxVelocity, maxVelocity);
        getBodyComponent().setLinearVelocity(velX, velY);
    }
}
