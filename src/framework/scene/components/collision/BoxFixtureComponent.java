package framework.scene.components.collision;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.contacts.common.Vec2;

/**
 * @author William Gervasio
 */
public class BoxFixtureComponent extends FixtureComponent {

    public BoxFixtureComponent(final float width, final float height, final float relativeX, final float relativeY, final float angle) {

        this(width, height, relativeX, relativeY, angle, false, new Filter());
    }

    public BoxFixtureComponent(final float width, final float height, final float relativeX, final float relativeY, final float angle, final boolean isSensor, Filter filter) {

        final PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height, new Vec2(relativeX, relativeY), angle);
        fixtureDef.setShape(shape);
        fixtureDef.setSensor(isSensor);
        fixtureDef.setFilter(filter);
    }
}
