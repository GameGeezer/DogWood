package framework.scene.components;

import framework.scene.Entity;

/**
 * Created by Will on 12/16/2014.
 */
public abstract class UpdateComponent extends Entity.EntityComponent {

    public abstract void update(int delta);

    @Override
    protected void onAttach() {

    }

    @Override
    protected void onDetach() {

    }
}
