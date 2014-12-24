package framework.scene.components;

import framework.scene.Entity.EntityComponent;

/**
 * An UpdateComponent is an EntityComponent that forces an update method to be implemented
 *
 * @author William Gervasio
 */
public abstract class UpdateComponent extends EntityComponent {

    /**
     * Update should be called once per frame.
     *
     * @param delta The time between frames
     */
    public abstract void update(int delta);

    @Override
    protected void onAttach() {

    }

    @Override
    protected void onDetach() {

    }
}
