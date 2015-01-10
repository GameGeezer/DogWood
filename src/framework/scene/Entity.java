package framework.scene;

import framework.util.exceptions.DogWoodException;

import java.util.*;

/**
 * @author William Gervasio
 */
public final class Entity implements Cloneable {

    private Map<Class, List<EntityComponent>> components = new HashMap<>();

    public Entity() {

    }

    public void addComponent(EntityComponent component) throws DogWoodException {

        // Make sure the component isn't already attached to another entity
        if (component.hasParent()) {

            throw new DogWoodException("Cannot attach component: Already attached to another entity");
        }
        // Check to see if a component of this type has been seen
        if (components.get(component.getClass()) == null) {
            // If it hasn't add a list of the type to the map for easy access
            components.put(component.getClass(), new ArrayList<>());
        }
        // Add the component to the map
        components.get(component.getClass()).add(component);
        // Set the component's parent to this object
        component.setParent(this);
        // Call the component specific "onAttach" method
        component.onAttach();
        // Loop each entry in the map
        components.entrySet().forEach((mapEntry) -> {
            // Check to see if the entry type is a parent type of the entity
            if ((mapEntry.getKey()).isAssignableFrom(component.getClass())) {
                // If it is then add a reference to the entity at that index.
                components.get(mapEntry.getKey()).add(component);
            }
        });

    }

    public void removeComponent(EntityComponent component) {

        // Return early if the list isn't there to prevent a null reference exception
        if (components.get(component.getClass()) == null) {

            return;
        }

        // Remove the component from every entry
        components.entrySet().forEach((mapEntry) -> {

            mapEntry.getValue().remove(component);
        });
        // Call the Component specific "onDetach" method
        component.onDetach();
        // Remove this as a parent from the component
        component.setParent(null);
    }

    public List<EntityComponent> getComponentsOfType(Class type) {

        // If there isn't a reference to components if this type they may still exist as parent components
        if (components.get(type) == null) {
            // Create a new list so all the related components can be compiled in one place
            List<EntityComponent> relatedComponents = new ArrayList<>();
            // Loop over every entry in the map
            components.entrySet().forEach((mapEntry) -> {
                // If the map key (A "Class" value) is an assignable type of "type" add it to the list
                if (type.isAssignableFrom(mapEntry.getKey())) {

                    relatedComponents.addAll(components.get(mapEntry.getKey()));
                }
            });
            // Add the new list to the map
            components.put(type, relatedComponents);
        }
        // Return the list
        return components.get(type);
    }

    public boolean hasComponentOfType(Class type) {

        List<EntityComponent> componentsOfType = components.get(type);

        return componentsOfType != null && componentsOfType.size() > 0;
    }

    public boolean hasComponent(EntityComponent component) {

        List<EntityComponent> componentsOfType = getComponentsOfType(component.getClass());

        return componentsOfType != null && componentsOfType.contains(component);
    }

    /**
     * Nested to hide the parentEntity variable from all classes except Entity.
     */
    public static abstract class EntityComponent implements Cloneable {

        private Entity parentEntity;

        public EntityComponent() {

        }

        public List<EntityComponent> getComponentsOfType(Class type) {

            return parentEntity.getComponentsOfType(type);
        }

        public Entity getParent() {

            return parentEntity;
        }

        public boolean hasParent() {

            return parentEntity != null;
        }

        protected void setParent(Entity entity) {

            this.parentEntity = entity;
        }

        protected abstract void onAttach();

        protected abstract void onDetach();
    }
}
