package framework.scene.components;

import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity.EntityComponent;

import java.util.List;

/**
 * A RenderComponent is a EntityComponent that forces a render(int delta) to be implemented.
 *
 * @author William Gervasio
 */
public abstract class RenderComponent extends EntityComponent {

    private ShaderProgram shader;

    public RenderComponent(ShaderProgram shader) {

        this.shader = shader;
    }

    public abstract void render(int delta);

    @Override
    protected void onAttach() {

        List<UniformComponent> uniformComponents = (List<UniformComponent>) (List<?>) getParent().getComponentsOfType(UniformComponent.class);

        uniformComponents.forEach((component) -> component.addListener(getShader()));
    }

    @Override
    protected void onDetach() {

        List<UniformComponent> uniformComponents = (List<UniformComponent>) (List<?>) getParent().getComponentsOfType(UniformComponent.class);

        uniformComponents.forEach((component) -> component.removeListener(getShader()));
    }

    @Override
    protected void onComponentAttachedToParent(EntityComponent component) {

        if(component instanceof UniformComponent) {

            ((UniformComponent) component).addListener(getShader());
        }
    }

    @Override
    protected void onComponentDetachedFromParent(EntityComponent component) {

        if(component instanceof UniformComponent) {

            ((UniformComponent) component).removeListener(getShader());
        }
    }

    public ShaderProgram getShader() {
        return shader;
    }
}
