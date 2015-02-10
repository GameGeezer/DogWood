package game.components.player.states;

import framework.scene.Entity;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.util.RangeUtil;
import game.SpriteAnimation;
import game.components.SpriteComponent;
import game.components.player.PlayerControllerComponent;

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
    public void move(Entity entity, float x, float y) {

        PhysicsBodyComponent body = (PhysicsBodyComponent) entity.getComponentsOfType(PhysicsBodyComponent.class).get(0);
        body.setLinearDampening(100f);
        body.applyForceToCenter(x * force, y * force);

        float velX = RangeUtil.forceIntoRange(body.getLinearVelocityX(), -maxVelocity, maxVelocity);
        float velY = RangeUtil.forceIntoRange(body.getLinearVelocityY(), -maxVelocity, maxVelocity);
        body.setLinearVelocity(velX, velY);

        SpriteComponent sprite = (SpriteComponent) entity.getComponentsOfType(SpriteComponent.class).get(0);
        PlayerControllerComponent controllerComponent = (PlayerControllerComponent) entity.getComponentsOfType(PlayerControllerComponent.class).get(0);
        int xIndex = controllerComponent.getAnimationFrame() % 3;
        int yIndex = controllerComponent.getAnimationFrame() / 3;
        sprite.setFrame(xIndex, yIndex);
    }
}
