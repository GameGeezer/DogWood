package framework.scene.components.collision;

import framework.scene.Entity;
import org.jbox2d.dynamics.FixtureDef;

/**
 * Created by Will on 1/27/2015.
 */
public abstract class FixtureComponent extends Entity.EntityComponent {

    protected FixtureDef fixtureDef = new FixtureDef();

    @Override
    protected void onAttach() {

    }

    @Override
    protected void onDetach() {

    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

    }

    public FixtureDef getFixture() {

        return fixtureDef;
    }
}
