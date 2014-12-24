package framework.scene.components;

import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.Entity.EntityComponent;

import java.util.List;

/**
 * @author William Gervasio
 */
public abstract class UniformComponent extends Entity.EntityComponent {

    public abstract void addListener(ShaderProgram shader);

    public abstract void removeListener(ShaderProgram shader);

    @Override
    protected void onAttach() {

        List<RenderComponent> renderComponents = (List<RenderComponent>)(List<?>) getParent().getComponentsOfType(RenderComponent.class);

        renderComponents.forEach((component) -> addListener(component.getShader()));
    }

    @Override
    protected void onDetach() {

        List<RenderComponent> renderComponents = (List<RenderComponent>)(List<?>) getParent().getComponentsOfType(RenderComponent.class);

        renderComponents.forEach((component) -> removeListener(component.getShader()));
    }
}
