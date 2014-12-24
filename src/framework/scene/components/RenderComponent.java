package framework.scene.components;

import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity.EntityComponent;

import java.util.List;

/**
 * Created by Will on 12/16/2014.
 */
public abstract class RenderComponent extends EntityComponent {

    public abstract void render(int delta);

    public abstract ShaderProgram getShader();

    @Override
    protected void onAttach() {

        List<UniformComponent> uniformComponents = (List<UniformComponent>)(List<?>) getParent().getComponentsOfType(UniformComponent.class);

        uniformComponents.forEach((component) -> component.addListener(getShader()));
    }

    @Override
    protected void onDetach() {

        List<UniformComponent> uniformComponents = (List<UniformComponent>)(List<?>) getParent().getComponentsOfType(UniformComponent.class);

        uniformComponents.forEach((component) -> component.removeListener(getShader()));
    }
}
