package framework.scene;

import framework.util.exceptions.EntityException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William Gervasio
 */
public class Entity {

    private final Map<Class, List<EntityComponent>> componentMap = new HashMap<>();

    public final void addComponent(final EntityComponent component) throws EntityException {

        // Make sure the component isn't already attached to another entity
        if (component.hasParent()) {

            throw new EntityException("Cannot attach component: Already attached to another entity");
        }
        // Check to see if a component of this type has been seen
        if (componentMap.get(component.getClass()) == null) {
            // If it hasn't add a list of the type to the map for easy access
            componentMap.put(component.getClass(), new ArrayList<>());
        }
        // Notify every component that a new one has been added in case they're interested
        componentMap.entrySet().forEach((mapEntry) -> mapEntry.getValue().forEach((componentEntry) -> componentEntry.onComponentAttachedToParent(component)));

        // Add the component to the map
        componentMap.get(component.getClass()).add(component);
        // Set the component's parent to this object
        component.setParent(this);
        // Call the component specific "onAttach" method
        component.onAttach();
        // Loop each entry in the map
        componentMap.entrySet().forEach((mapEntry) -> {
            // Check to see if the entry type is a parent type of the entity
            if ((mapEntry.getKey()).isAssignableFrom(component.getClass()) && !mapEntry.getKey().equals(component.getClass())) {
                // If it is then add a reference to the entity at that index.
                componentMap.get(mapEntry.getKey()).add(component);
            }
        });
    }

    public final void removeComponent(final EntityComponent component) {

        // Return early if the list isn't there to prevent a null reference exception
        if (componentMap.get(component.getClass()) == null) {

            return;
        }

        // Remove the component from every entry
        componentMap.entrySet().forEach((mapEntry) -> {

            mapEntry.getValue().remove(component);

            mapEntry.getValue().forEach((componentEntry) -> componentEntry.onComponentDetachedFromParent(component));
        });
        // Call the Component specific "onDetach" method
        component.onDetach();
        // Remove this as a parent from the component
        component.setParent(null);
    }

    public final List<EntityComponent> getComponentsOfType(final Class type) {

        // If there isn't a reference to components if this type they may still exist as parent components
        if (componentMap.get(type) == null) {
            // Create a new list so all the related components can be compiled in one place
            List<EntityComponent> relatedComponents = new ArrayList<>();
            // Loop over every entry in the map
            componentMap.entrySet().forEach((mapEntry) -> {
                // If the map key (A "Class" value) is an assignable type of "type" add it to the list
                if (type.isAssignableFrom(mapEntry.getKey())) {

                    relatedComponents.addAll(componentMap.get(mapEntry.getKey()));
                }
            });
            // Add the new list to the map
            componentMap.put(type, relatedComponents);
        }
        // Return the list
        return componentMap.get(type);
    }

    public final boolean hasComponentOfType(final Class type) {

        List<EntityComponent> componentsOfType = componentMap.get(type);

        return componentsOfType != null && componentsOfType.size() > 0;
    }

    public final boolean hasComponent(EntityComponent component) {

        List<EntityComponent> componentsOfType = getComponentsOfType(component.getClass());

        return componentsOfType != null && componentsOfType.contains(component);
    }

    public final EntityComponent getFirstComponentOfType(final Class type) {

        return hasComponentOfType(type) ? getComponentsOfType(type).get(0) : null;
    }

    /**
     * Nested to hide the parentEntity variable from all classes except Entity.
     */
    public static abstract class EntityComponent {

        private Entity parentEntity;

        public List<EntityComponent> getComponentsOfType(final Class type) {

            return parentEntity.getComponentsOfType(type);
        }

        public Entity getParent() {

            return parentEntity;
        }

        public boolean hasParent() {

            return parentEntity != null;
        }

        protected void removeSelfFromParent() {

            parentEntity.removeComponent(this);
        }

        protected abstract void onAttach() throws EntityException;

        protected abstract void onDetach();

        protected abstract void onComponentAttachedToParent(final EntityComponent component);

        protected abstract void onComponentDetachedFromParent(final EntityComponent component);

        private void setParent(final Entity entity) {

            this.parentEntity = entity;
        }
    }
}
