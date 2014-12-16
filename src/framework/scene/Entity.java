package framework.scene;

import framework.scene.components.DynamicEntityComponent;
import framework.scene.components.EntityComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William Gervasio
 */
public class Entity {

    private Map<Class, List<EntityComponent>> components = new HashMap<>();
    private List<DynamicEntityComponent> dynamicComponents = new ArrayList<>();

    public Entity() {

    }

    public void update(int delta) {
        for(DynamicEntityComponent component : dynamicComponents) {
            component.update(delta);
        }
    }

    public void addComponent(EntityComponent component) {
        if(components.get(component.getClass()) == null) {
            components.put(component.getClass(), new ArrayList<>());
        }
        components.get(component.getClass()).add(component);

        if(component instanceof DynamicEntityComponent) {
            dynamicComponents.add((DynamicEntityComponent) component);
        }
    }

    public void removeComponent(EntityComponent component) {
        if(components.get(component.getClass()) == null) {
            return;
        }
        components.get(component.getClass()).remove(component);

        if(component instanceof DynamicEntityComponent) {
            dynamicComponents.remove(component);
        }
    }

    public List<EntityComponent> getComponentsOfType(Class<? extends EntityComponent> type) {
        return components.get(type);
    }

    public boolean hasComponentOfType(Class<? extends EntityComponent> type) {
        return getComponentsOfType(type) != null;
    }

    public boolean hasComponent(EntityComponent component) {
        List<EntityComponent> componentsOfType = getComponentsOfType(component.getClass());
        return componentsOfType != null && componentsOfType.contains(component);
    }
}
