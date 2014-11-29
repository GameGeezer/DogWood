package framework.scene;

import framework.scene.components.IDynamicEntityComponent;
import framework.scene.components.IEntityComponent;
import framework.util.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William Gervasio
 */
public class Entity {

    private Map<Class, List<IEntityComponent>> components = new HashMap<Class, List<IEntityComponent>>();
    private List<IDynamicEntityComponent> dynamicComponents = new ArrayList<IDynamicEntityComponent>();

    public Entity() {

    }

    public void update(int delta) {
        for(IDynamicEntityComponent component : dynamicComponents) {
            component.update(delta);
        }
    }

    public void addComponent(IEntityComponent component) {

        if(components.get(component.getClass()) == null) {
            components.put(component.getClass(), new ArrayList<IEntityComponent>());
        }
        components.get(component.getClass()).add(component);

        if(component instanceof IDynamicEntityComponent) {
            dynamicComponents.add((IDynamicEntityComponent)component);
        }
    }

    public void removeComponent(IEntityComponent component) {
        if(components.get(component.getClass()) == null) {
            return;
        }
        components.get(component.getClass()).remove(component);

        if(component instanceof IDynamicEntityComponent) {
            dynamicComponents.remove(component);
        }
    }

    public List<IEntityComponent> getComponentsOfType(Class<? extends IEntityComponent> type) {
        return components.get(type);
    }

    public boolean hasComponent(IEntityComponent component) {
        List<IEntityComponent> componentsOfType = getComponentsOfType(component.getClass());
        return componentsOfType == null ? false : componentsOfType.contains(component);
    }
}
