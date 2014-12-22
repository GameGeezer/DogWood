package framework.scene;

import framework.input.Keyboard;
import framework.scene.components.*;

import java.util.*;

/**
 * @author William Gervasio
 */
public class Entity implements Cloneable {

    private Map<Class, List<EntityComponent>> components = new HashMap<>();
    private List<IDynamicComponent> dynamicComponents = new ArrayList<>();
    private List<IRenderComponent> renderComponents = new ArrayList<>();
    private List<IUniformComponent> uniformComponents = new ArrayList<>();

    public Entity() {

    }

    public void update(int delta) {
        for(IDynamicComponent component : dynamicComponents) {
            component.update(delta);
        }
    }

    public void render(int delta) {
        for(IRenderComponent component : renderComponents) {
            component.render(delta);
        }
    }

    public void addComponent(EntityComponent component) {
        if(components.get(component.getClass()) == null) {
            components.put(component.getClass(), new ArrayList<>());
        }
        components.get(component.getClass()).add(component);
        component.setParent(this);
        if(component instanceof IDynamicComponent) {
            dynamicComponents.add((IDynamicComponent) component);
        }
        if(component instanceof IRenderComponent) {
            renderComponents.add((IRenderComponent) component);
            subscribeToUniforms((IRenderComponent) component);
        }
        if(component instanceof IUniformComponent) {
            uniformComponents.add((IUniformComponent) component);
            addSubscriptionToRenderComponents((IUniformComponent) component);
        }

        if(component instanceof IKeyboardComponent) {
            Keyboard.addListener((IKeyboardComponent) component);
        }
    }

    public void removeComponent(EntityComponent component) {
        if(components.get(component.getClass()) == null) {
            return;
        }
        components.get(component.getClass()).remove(component);

        if(component instanceof IDynamicComponent) {
            dynamicComponents.remove(component);
        }
        if(component instanceof IRenderComponent) {
            renderComponents.remove(component);
            unsubscribeFromUniforms((IRenderComponent) component);
        }
        if(component instanceof IUniformComponent) {
            uniformComponents.remove(component);
            removeSubscriptionFromRenderComponents((IUniformComponent) component);
        }

        if(component instanceof IKeyboardComponent) {
            Keyboard.removeListener((IKeyboardComponent) component);
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

    private void addSubscriptionToRenderComponents(IUniformComponent uniformComponent) {
        for(IRenderComponent renderComponent : renderComponents) {
            uniformComponent.addListener(renderComponent.getShader());
        }
    }

    private void removeSubscriptionFromRenderComponents(IUniformComponent uniformComponent) {
        for(IRenderComponent renderComponent : renderComponents) {
            uniformComponent.removeListener(renderComponent.getShader());
        }
    }

    private void subscribeToUniforms(IRenderComponent renderComponent) {
        for(IUniformComponent uniformComponent : uniformComponents) {
            uniformComponent.addListener(renderComponent.getShader());
        }
    }

    private void unsubscribeFromUniforms(IRenderComponent renderComponent) {
        for(IUniformComponent uniformComponent : uniformComponents) {
            uniformComponent.removeListener(renderComponent.getShader());
        }
    }

    /**
     * TODO
     * @return
     */
    public Entity clone() {
        Entity entity = new Entity();
        Iterator it = components.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
           // entity.addComponent(((EntityComponent) pairs.getValue()).clone());
        }

        return entity;
    }

    /**
     * Nested to hide the parentEntity variable from all classes except Entity.
     */
    public static abstract class EntityComponent<T extends EntityComponent> implements Cloneable {

        private Entity parentEntity;

        public EntityComponent() {

        }

        public List<EntityComponent> getComponentsOfType(Class<? extends EntityComponent> type) {
            return parentEntity.getComponentsOfType(type);
        }

        protected void setParent(Entity entity) {
            this.parentEntity = entity;
        }

        public Entity getParent() {
            return parentEntity;
        }

        /*
        @Override
        public abstract T clone();
        */
    }
}
