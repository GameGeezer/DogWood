package game.components;

import framework.scene.Entity;
import framework.scene.components.UpdateComponent;
import framework.scene.components.util.TransformComponent;

import java.util.List;

/**
 * Created by Will on 1/13/2015.
 */
public class ArcAtDepthComponent extends UpdateComponent{

    private List<TransformComponent> transformComponents;
    private float rotationAlongArc = (float) Math.PI / 4f;
    private float depth = -5;

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

        TransformComponent transformComponent = transformComponents.get(0);

        transformComponent.setOrientationEuler(0, rotationAlongArc * transformComponent.getX(), 0);
        transformComponent.setZ(depth + Math.abs(transformComponent.getX()));
    }
}
