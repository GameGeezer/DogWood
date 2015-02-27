package framework.scene.components.graphics;

import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.uniform.IUniformWrapper;
import framework.scene.Entity.EntityComponent;

import java.util.List;

/**
 * A RenderComponent is a EntityComponent that forces a render(int delta) to be implemented.
 *
 * @author William Gervasio
 */
public abstract class RenderComponent extends EntityComponent {

    private final ShaderProgram shader;

    public RenderComponent(final ShaderProgram shader) {

        this.shader = shader;
    }

    public abstract void render(final int delta);

    @Override
    protected void onAttach() {

        final List<IUniformWrapper> uniformComponents = (List<IUniformWrapper>) (List<?>) getParent().getComponentsOfType(IUniformWrapper.class);

        uniformComponents.forEach((component) -> component.addListener(getShader()));
    }

    @Override
    protected void onDetach() {

        final List<IUniformWrapper> uniformComponents = (List<IUniformWrapper>) (List<?>) getParent().getComponentsOfType(IUniformWrapper.class);

        uniformComponents.forEach((component) -> component.removeListener(getShader()));
    }

    @Override
    protected void onComponentAttachedToParent(final EntityComponent component) {

        if (component instanceof IUniformWrapper) {

            ((IUniformWrapper) component).addListener(getShader());
        }
    }

    @Override
    protected void onComponentDetachedFromParent(final EntityComponent component) {

        if (component instanceof IUniformWrapper) {

            ((IUniformWrapper) component).removeListener(getShader());
        }
    }

    public ShaderProgram getShader() {

        return shader;
    }
}
