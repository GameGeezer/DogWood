package game.components.player;

import framework.scene.Entity;
import framework.scene.components.UpdateComponent;
import framework.scene.components.util.TransformComponent;
import framework.util.RangeUtil;
import framework.util.math.Vector2;
import game.components.DynamicComponent;

import java.util.List;

/**
 * Created by Will on 12/24/2014.
 */
public class PlayerUpdateComponent extends UpdateComponent {

    private List<DynamicComponent> dynamicComponents;
    private List<PlayerControllerComponent> controllerComponents;

    @Override
    protected void onAttach() {

        dynamicComponents = (List<DynamicComponent>) (List<?>) getParent().getComponentsOfType(DynamicComponent.class);

        controllerComponents = (List<PlayerControllerComponent>) (List<?>) getParent().getComponentsOfType(PlayerControllerComponent.class);
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

        DynamicComponent dynamicComponent = dynamicComponents.get(0);

        PlayerControllerComponent controllerComponent = controllerComponents.get(0);

        if (controllerComponent.isMoveLeft()) {

            dynamicComponent.setAccelerationX(-1.8f);
        }

        if (controllerComponent.isMoveRight()) {

            dynamicComponent.setAccelerationX(1.8f);
        }

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
    }
}
