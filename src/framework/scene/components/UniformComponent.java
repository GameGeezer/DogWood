package framework.scene.components;

import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.Entity.EntityComponent;

import java.util.List;

/**
 * Created by Will on 12/16/2014.
 */
public abstract class UniformComponent extends Entity.EntityComponent {

    public abstract void addListener(ShaderProgram shader);

    public abstract void removeListener(ShaderProgram shader);

    @Override
    protected void onAttach() {

        List<EntityComponent> renderComponents = getParent().getComponentsOfType(RenderComponent.class);

        for(EntityComponent component : renderComponents) {

            RenderComponent renderCast = (RenderComponent) component;

            addListener(renderCast.getShader());
        }
    }

    @Override
    protected void onDetach() {

        List<EntityComponent> renderComponents = getParent().getComponentsOfType(RenderComponent.class);

        for(EntityComponent component : renderComponents) {

            RenderComponent renderCast = (RenderComponent) component;

            removeListener(renderCast.getShader());
        }
    }
}
