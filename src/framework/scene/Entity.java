package framework.scene;

import framework.scene.components.EntityComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William Gervasio
 */
public class Entity {

    private Map<Class, List<EntityComponent>> components = new HashMap<Class, List<EntityComponent>>();

    public Entity() {

    }

    public void addComponent(EntityComponent component) {

        if(components.get(component.getClass()) == null) {
            components.put(component.getClass(), new ArrayList<EntityComponent>());
        }
        components.get(component.getClass()).add(component);
    }

    public List<EntityComponent> getComponentsOfType(Class<? extends EntityComponent> type) {
        return components.get(type);
    }

    public boolean hasComponent(EntityComponent component) {
        List<EntityComponent> componentsOfType = getComponentsOfType(component.getClass());
        return componentsOfType == null ? false : componentsOfType.contains(component);
    }
}
