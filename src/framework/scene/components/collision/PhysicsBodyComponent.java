package framework.scene.components.collision;

import framework.scene.Entity;
import framework.scene.PhysicsWorld;
import framework.scene.components.util.TransformComponent;
import framework.scene.components.util.UpdateComponent;
import framework.util.exceptions.EntityException;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Will on 1/27/2015.
 */
public class PhysicsBodyComponent extends UpdateComponent {

    private Body body;

    private Map<FixtureComponent, Fixture> componentToFixtureMap = new HashMap<>();

    private List<TransformComponent> transformComponents;

    private Vec2 tempVec = new Vec2();

    public PhysicsBodyComponent(BodyType type, float x, float y) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.setPosition(new Vec2(x, y));
        bodyDef.type = type;

        body = PhysicsWorld.WORLD.createBody(bodyDef);
    }

    public void update(int delta) {

        TransformComponent transform = transformComponents.get(0);

        transform.setX(body.getPosition().x);
        transform.setY(body.getPosition().y);
    }

    public void move(float x, float y) {

        body.getTransform().p.addLocal(x, y);
    }

    public void setLinearVelocity(float x, float y) {

        body.setLinearVelocity(tempVec.set(x, y));
    }

    public float getLinearVelocityX(){

        return body.getLinearVelocity().x;
    }

    public float getLinearVelocityY(){

        return body.getLinearVelocity().y;
    }

    public void applyFor() {
        //body.a();
    }

    @Override
    protected void onAttach() throws EntityException {

        if(getParent().getComponentsOfType(PhysicsBodyComponent.class).size() > 1) {

            removeSelfFromParent();

            throw  new EntityException("Only one PhysicsBodyComponent may be attached to an entity");
        }

        List<FixtureComponent> fixtures = (List<FixtureComponent>) (List<?>) getParent().getComponentsOfType(FixtureComponent.class);

        for(FixtureComponent fixtureComponent : fixtures) {

            Fixture fixture = body.createFixture(fixtureComponent.getFixture());

            componentToFixtureMap.put(fixtureComponent, fixture);
        }

        transformComponents = (List<TransformComponent>) (List<?>) getParent().getComponentsOfType(TransformComponent.class);
    }

    @Override
    protected void onDetach() {

        List<FixtureComponent> fixtures = (List<FixtureComponent>) (List<?>) getParent().getComponentsOfType(FixtureComponent.class);

        for(FixtureComponent fixtureComponent : fixtures) {

            body.destroyFixture(componentToFixtureMap.get(fixtureComponent));

            componentToFixtureMap.remove(fixtureComponent);
        }
    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

        if(component instanceof FixtureComponent) {

            FixtureComponent fixtureComponent = (FixtureComponent) component;

            Fixture fixture = body.createFixture(fixtureComponent.getFixture());

            componentToFixtureMap.put(fixtureComponent, fixture);
        }
    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

        if(component instanceof FixtureComponent) {

            body.destroyFixture(componentToFixtureMap.get(component));

            FixtureComponent fixtureComponent = (FixtureComponent) component;

            componentToFixtureMap.remove(fixtureComponent);
        }
    }
}
