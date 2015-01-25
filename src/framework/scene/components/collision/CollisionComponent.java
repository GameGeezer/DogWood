package framework.scene.components.collision;

import framework.scene.Entity;
import framework.scene.components.util.TransformComponent;
import framework.util.math.Transform;

/**
 * Created by Will on 1/11/2015.
 */
public abstract class CollisionComponent extends Entity.EntityComponent {

    private TransformComponent parentTransform;

    @Override
    protected void onAttach() {

       if(getParent().hasComponentOfType(TransformComponent.class)) {

           parentTransform = (TransformComponent) getParent().getComponentsOfType(TransformComponent.class).get(0);
       }

    }

    @Override
    protected void onDetach() {

    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

        if(parentTransform == null && component instanceof TransformComponent) {

            parentTransform = (TransformComponent) component;
        }
    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

        if(parentTransform != null && component.equals(parentTransform)) {

            parentTransform = null;
        }
    }

    public abstract float getLocalX();

    public abstract float getLocalY();

    public abstract float getWorldX();

    public abstract float getWorldY();

    protected float getParentTransformX() {

        if(parentTransform == null)
            return 0;

        return parentTransform.getX();
    }

    protected float getParentTransformY() {

        if(parentTransform == null)
            return 0;

        return parentTransform.getY();
    }
}
