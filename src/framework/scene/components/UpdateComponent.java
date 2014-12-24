package framework.scene.components;

import framework.scene.Entity.EntityComponent;

/**
 * @author William Gervasio
 */
public abstract class UpdateComponent extends EntityComponent {

    public abstract void update(int delta);

    @Override
    protected void onAttach() {

    }

    @Override
    protected void onDetach() {

    }
}
