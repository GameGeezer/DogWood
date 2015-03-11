package game.states;

import framework.scene.components.collision.PhysicsBodyComponent;
import framework.util.RangeUtil;
import framework.util.Timer;

/**
 * @author WIlliam Gervasio
 */
public class TimedMovementState extends MovementState {

    private float force;
    private float maxVelocity;
    private float rollTime;
    private float linearDampening;

    private Timer rollTimer = new Timer();

    public TimedMovementState(PhysicsBodyComponent bodyComponent, float linearDampening, float force, float maxVelocity, float rollTime) {
        super(bodyComponent);

        this.linearDampening = linearDampening;
        this.force = force;
        this.maxVelocity = maxVelocity;
        this.rollTime = rollTime;
    }

    @Override
    protected void onLeaveTop() {

        getBodyComponent().setLinearDampening(100f);
    }

    @Override
    protected void onBecomeTop() {

        getBodyComponent().setLinearDampening(linearDampening);
        rollTimer.reset();
        rollTimer.start();
    }

    @Override
    public void move() {

        if(rollTimer.getElapsedTimeMS() >= rollTime) {
            popStack();
            return;
        }

        getBodyComponent().applyForceToCenter(getMoveDirection().x * force, getMoveDirection().y  * force);

        float velX = RangeUtil.forceIntoRange(getBodyComponent().getLinearVelocityX(), -maxVelocity, maxVelocity);
        float velY = RangeUtil.forceIntoRange(getBodyComponent().getLinearVelocityY(), -maxVelocity, maxVelocity);
        getBodyComponent().setLinearVelocity(velX, velY);
    }
}
