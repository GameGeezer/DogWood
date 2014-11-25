package framework.scene.components;

/**
 * A DynamicEntityComponent is an EntityComponent that can be updated. I distinguish
 * between dynamic and static EntityComponents to save checks in the Entity object.
 * @author William Gervasio
 */
public interface DynamicEntityComponent extends EntityComponent{
    public abstract void update(int delta);
}
