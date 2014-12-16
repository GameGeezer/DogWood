package framework.scene.components;

import framework.scene.Entity;

import java.util.List;

/**
 * Created by Will on 12/15/2014.
 */
public abstract class EntityComponent {

    private Entity parentEntity;

    public EntityComponent() {
        this(null);
    }

    public EntityComponent(Entity parentEntity) {
        this.parentEntity = parentEntity;
    }

    public List<EntityComponent> getComponentsOfType(Class<? extends EntityComponent> type) {
        return parentEntity.getComponentsOfType(type);
    }
}
