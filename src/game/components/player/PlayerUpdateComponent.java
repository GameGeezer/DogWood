package game.components.player;

import framework.scene.components.util.UpdateComponent;
import framework.scene.components.util.TransformComponent;

import java.util.List;

/**
 * Created by Will on 12/24/2014.
 */
public class PlayerUpdateComponent extends UpdateComponent {

    @Override
    public void update(int delta) {

        List<TransformComponent> transformComponents = (List<TransformComponent>) (List<?>) getParent().getComponentsOfType(TransformComponent.class);

        if (transformComponents.size() == 0)
            return;

        List<PlayerControllerComponent> controllerComponents = (List<PlayerControllerComponent>) (List<?>) getParent().getComponentsOfType(PlayerControllerComponent.class);

        if (controllerComponents.size() == 0)
            return;

        TransformComponent transformComponent = transformComponents.get(0);

        PlayerControllerComponent controllerComponent = controllerComponents.get(0);

        if (controllerComponent.isMoveLeft())
            transformComponent.translate(-0.05f, 0, 0);

        if (controllerComponent.isMoveRight())
            transformComponent.translate(0.05f, 0, 0);

        transformComponent.rotateEuler(0, (float) Math.PI / 100, 0);
    }
}
