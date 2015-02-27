package framework.scene.components.collision;

import framework.scene.Entity;
import framework.scene.components.TransformComponent;
import framework.scene.components.UpdateComponent;
import framework.util.exceptions.EntityException;
import framework.window.Application;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.common.Vec2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William Gervasio
 */
public final class PhysicsBodyComponent extends UpdateComponent {

    private TransformComponent transformComponent;

    private final Body body;

    private final Map<FixtureComponent, Fixture> componentToFixtureMap = new HashMap<>();

    private final Vec2 tempVec = new Vec2();

    public PhysicsBodyComponent(final BodyType type) {

        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        body = Application.PHYSICS_WORLD.createBody(bodyDef);
    }

    @Override
    protected void onAttach() throws EntityException {

        if (getParent().getComponentsOfType(PhysicsBodyComponent.class).size() > 1) {

            removeSelfFromParent();

            throw new EntityException("Only one PhysicsBodyComponent may be attached to an entity");
        }

        final List<FixtureComponent> fixtures = (List<FixtureComponent>) (List<?>) getParent().getComponentsOfType(FixtureComponent.class);

        for (FixtureComponent fixtureComponent : fixtures) {

            final Fixture fixture = body.createFixture(fixtureComponent.getFixture());

            componentToFixtureMap.put(fixtureComponent, fixture);
        }

        transformComponent = (TransformComponent) getParent().getFirstComponentOfType(TransformComponent.class);

        if(transformComponent != null) {

            body.setTransform(tempVec.set(transformComponent.getX(), transformComponent.getY()), body.getTransform().q.getAngle());
        }
    }

    @Override
    protected void onDetach() {

        final List<FixtureComponent> fixtures = (List<FixtureComponent>) (List<?>) getParent().getComponentsOfType(FixtureComponent.class);

        for (FixtureComponent fixtureComponent : fixtures) {

            body.destroyFixture(componentToFixtureMap.get(fixtureComponent));

            componentToFixtureMap.remove(fixtureComponent);
        }
    }

    @Override
    protected void onComponentAttachedToParent(final Entity.EntityComponent component) {

        if (component instanceof FixtureComponent) {

            final FixtureComponent fixtureComponent = (FixtureComponent) component;

            final Fixture fixture = body.createFixture(fixtureComponent.getFixture());

            componentToFixtureMap.put(fixtureComponent, fixture);
        }

        if(component instanceof TransformComponent && transformComponent == null) {

            transformComponent = (TransformComponent) component;

            body.setTransform(tempVec.set(transformComponent.getX(), transformComponent.getY()), body.getTransform().q.getAngle());
        }
    }

    @Override
    protected void onComponentDetachedFromParent(final Entity.EntityComponent component) {

        if (component instanceof FixtureComponent) {

            body.destroyFixture(componentToFixtureMap.get(component));

            FixtureComponent fixtureComponent = (FixtureComponent) component;

            componentToFixtureMap.remove(fixtureComponent);
        }

        if(component instanceof TransformComponent) {

            transformComponent = null;
        }
    }

    @Override
    public void update(final int delta) {

        transformComponent.setX(body.getPosition().x);
        transformComponent.setY(body.getPosition().y);
    }

    public void move(final float x, final float y) {

        body.getTransform().p.addLocal(x, y);
    }

    public void setLinearVelocity(final float x, final float y) {

        body.setLinearVelocity(tempVec.set(x, y));
    }

    public void applyForceToCenter(final float x, final float y) {

        body.applyForceToCenter(tempVec.set(x, y));
    }

    public float getLinearVelocityX() {

        return body.getLinearVelocity().x;
    }

    public float getLinearVelocityY() {

        return body.getLinearVelocity().y;
    }

    public  void setLinearDampening(final float amount) {

        body.setLinearDamping(amount);
    }
}
