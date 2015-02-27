package framework.scene.components.collision;

import framework.scene.Entity;
import org.jbox2d.dynamics.FixtureDef;

/**
 * @author William Gervasio
 */
public abstract class FixtureComponent extends Entity.EntityComponent {

    protected final FixtureDef fixtureDef = new FixtureDef();

    @Override
    protected void onAttach() {

    }

    @Override
    protected void onDetach() {

    }

    @Override
    protected void onComponentAttachedToParent(final Entity.EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(final Entity.EntityComponent component) {

    }

    public FixtureDef getFixture() {

        return fixtureDef;
    }
}
