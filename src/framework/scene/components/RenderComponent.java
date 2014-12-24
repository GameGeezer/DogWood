package framework.scene.components;

import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.Entity.EntityComponent;

import java.util.List;

/**
 * Created by Will on 12/16/2014.
 */
public abstract class RenderComponent extends Entity.EntityComponent {

    public abstract void render(int delta);

    public abstract ShaderProgram getShader();

    @Override
    protected void onAttach() {

        List<EntityComponent> uniformComponents = getParent().getComponentsOfType(UniformComponent.class);

        for(EntityComponent component : uniformComponents) {

            UniformComponent uniformCast = (UniformComponent) component;

            uniformCast.addListener(getShader());
        }
    }

    @Override
    protected void onDetach() {

        List<EntityComponent> uniformComponents = getParent().getComponentsOfType(UniformComponent.class);

        for(EntityComponent component : uniformComponents) {

            UniformComponent uniformCast = (UniformComponent) component;

            uniformCast.removeListener(getShader());
        }
    }
}
