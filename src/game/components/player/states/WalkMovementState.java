package game.components.player.states;

import framework.scene.Entity;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.util.RangeUtil;
import game.components.player.PlayerUpdateComponent;

/**
 * Created by Will on 2/2/2015.
 */
public class WalkMovementState extends MovementState {



    private float force;
    private float maxVelocity;

    private int animationStartTime = 0;

    public WalkMovementState(PhysicsBodyComponent bodyComponent, float force, float maxVelocity) {
        super(bodyComponent);

        this.force = force;
        this.maxVelocity = maxVelocity;
    }

    @Override
    protected void onLeaveTop() {
        getBodyComponent().setLinearDampening(0f);

    }

    @Override
    protected void onBecomeTop() {
        getBodyComponent().setLinearDampening(100f);
    }

    @Override
    public void move() {

        getBodyComponent().applyForceToCenter(getRollDirection().getX() * force, getRollDirection().getY() * force);

        float velX = RangeUtil.forceIntoRange(getBodyComponent().getLinearVelocityX(), -maxVelocity, maxVelocity);
        float velY = RangeUtil.forceIntoRange(getBodyComponent().getLinearVelocityY(), -maxVelocity, maxVelocity);
        getBodyComponent().setLinearVelocity(velX, velY);
    }
}
