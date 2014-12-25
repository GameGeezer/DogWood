package framework.scene;

import java.util.*;

/**
 * @author William Gervasio
 */
public final class Entity implements Cloneable {

    private Map<Class, List<EntityComponent>> components = new HashMap<>();

    public Entity() {

    }

    public void addComponent(EntityComponent component) {

        if(components.get(component.getClass()) == null) {

            components.put(component.getClass(), new ArrayList<>());
        }

        components.get(component.getClass()).add(component);

        component.setParent(this);

        component.onAttach();

        components.entrySet().forEach((mapEntry) -> {

            if((mapEntry.getKey()).isAssignableFrom(component.getClass())) {

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

        protected void setParent(Entity entity) {
            this.parentEntity = entity;
        }

        protected abstract void onAttach();

        protected abstract void onDetach();
    }
}
