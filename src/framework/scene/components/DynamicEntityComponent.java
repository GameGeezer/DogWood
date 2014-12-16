package framework.scene.components;

import framework.scene.Entity;

/**
 * Created by Will on 12/15/2014.
 */
public abstract class DynamicEntityComponent extends EntityComponent {

    public DynamicEntityComponent(Entity parentEntity) {
        super(parentEntity);
    }

    public abstract void update(int delta);
}
