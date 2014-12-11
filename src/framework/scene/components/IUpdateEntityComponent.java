package framework.scene.components;

import framework.scene.Entity;

/**
 * Created by Will on 12/10/2014.
 */
public abstract class IUpdateEntityComponent extends IEntityComponent {

    public IUpdateEntityComponent(Entity parentEntity) {
        super(parentEntity);
    }

    public abstract void update(int delta);
}
