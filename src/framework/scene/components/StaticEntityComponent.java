package framework.scene.components;

/**
 * A StaticEntityObject is an EntityComponent that will not request an update. I distinguish
 * between dynamic and static EntityComponents to save checks in the Entity object.
 * @author William Gervasio
 */
public interface StaticEntityComponent extends EntityComponent{
}
