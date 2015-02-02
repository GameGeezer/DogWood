package game.components.player;

import framework.scene.Entity;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.scene.components.util.UpdateComponent;
import framework.scene.components.util.TransformComponent;

import java.util.List;

/**
 * Created by Will on 12/24/2014.
 */
public class PlayerUpdateComponent extends UpdateComponent {

    private List<PlayerControllerComponent> controllerComponents;
    private List<PhysicsBodyComponent> bodyComponents;
    private float rotationAlongArc = (float) Math.PI / 5f / 2f;
    private float depth = -5;

    @Override
    protected void onAttach() {

        controllerComponents = (List<PlayerControllerComponent>) (List<?>) getParent().getComponentsOfType(PlayerControllerComponent.class);

        bodyComponents = (List<PhysicsBodyComponent>) (List<?>) getParent().getComponentsOfType(PhysicsBodyComponent.class);
    }

    @Override
    protected void onDetach() {

    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

    }

    @Override
    public void update(int delta) {


        PhysicsBodyComponent bodyComponent = bodyComponents.get(0);

        PlayerControllerComponent controllerComponent = controllerComponents.get(0);

        if (controllerComponent.isMoveLeft()) {
            bodyComponent.setLinearVelocity(-10, 0);
        }

        if (controllerComponent.isMoveRight()) {

            bodyComponent.setLinearVelocity(10, 0);
        }

        if (controllerComponent.isMoveUp()) {

            bodyComponent.setLinearVelocity(0, 10);
        }

        if (controllerComponent.isMoveDown()) {

            bodyComponent.setLinearVelocity(0, -10);
        }

        /*
        if(!controllerComponent.isMoveLeft() && !controllerComponent.isMoveRight() || controllerComponent.isMoveLeft() && controllerComponent.isMoveRight()) {

            if(dynamicComponent.getVelocityX() > 0.05f) {

                dynamicComponent.setAccelerationX(-1f);
            } else if(dynamicComponent.getVelocityX() < -0.05f)

                dynamicComponent.setAccelerationX(1f);
            else {
                dynamicComponent.setVelocityX(0);
                dynamicComponent.setAccelerationX(0);
            }
        }

        if(!controllerComponent.isMoveUp() && !controllerComponent.isMoveDown() || controllerComponent.isMoveUp() && controllerComponent.isMoveDown()) {

            if(dynamicComponent.getVelocityY() > 0.05f) {

                dynamicComponent.setAccelerationY(-1f);
            } else if(dynamicComponent.getVelocityY() < -0.05f)

                dynamicComponent.setAccelerationY(1f);
            else {
                dynamicComponent.setVelocityY(0);
                dynamicComponent.setAccelerationY(0);
            }
        }
        */
    }
}
