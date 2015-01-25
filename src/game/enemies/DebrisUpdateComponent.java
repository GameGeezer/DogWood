package game.enemies;

import framework.scene.Entity;
import framework.scene.components.util.UpdateComponent;
import framework.scene.components.util.TransformComponent;

import java.util.List;

/**
 * Created by Will on 1/13/2015.
 */
public class DebrisUpdateComponent extends UpdateComponent {

    private List<TransformComponent> transformComponents;

    @Override
    protected void onAttach() {

        transformComponents = (List<TransformComponent>) (List<?>) getParent().getComponentsOfType(TransformComponent.class);
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

        TransformComponent dynamicComponent = transformComponents.get(0);

        float deltaOverAThousand = delta / 1000f;

        dynamicComponent.rotateEuler(0, 0,  (float)Math.PI * deltaOverAThousand );

    }
}
