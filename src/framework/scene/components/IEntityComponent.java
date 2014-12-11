package framework.scene.components;

import framework.scene.Entity;

import java.util.List;

/**
 * Created by Will on 11/26/2014.
 */
public abstract class IEntityComponent {

    private Entity parentEntity;

    public IEntityComponent(Entity parentEntity) {
        this.parentEntity = parentEntity;
    }

    public List<IEntityComponent> getComponentsOfType(Class<? extends IEntityComponent> type) {
        return parentEntity.getComponentsOfType(type);
    }
}


