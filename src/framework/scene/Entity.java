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
        if(component.hasParent()) {

            throw new DogWoodException("Cannot attach component: Already attached to another entity");
        }
        // Check to see if a component of this type has been seen
        if(components.get(component.getClass()) == null) {
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
            if((mapEntry.getKey()).isAssignableFrom(component.getClass())) {
                // If it is then add a reference to the entity at that index.
                components.get(mapEntry.getKey()).add(component);
            }
        });

    }

    public void removeComponent(EntityComponent component) {

        if(components.get(component.getClass()) == null) {

            return;
        }

        components.get(component.getClass()).remove(component);

        component.setParent(null);

        component.onDetach();
    }

    public List<EntityComponent> getComponentsOfType(Class type) {

        if(!hasComponentOfType(type)) {

            List<EntityComponent> relatedComponents = new ArrayList<>();

            components.entrySet().forEach((mapEntry) -> {

                if(type.isAssignableFrom(mapEntry.getKey())) {

                    relatedComponents.addAll(components.get(mapEntry.getKey()));
                }
            });

            components.put(type, relatedComponents);
        }

        return components.get(type);
    }

    public boolean hasComponentOfType(Class type) {
        return components.get(type) != null;
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
