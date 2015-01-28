package framework.scene.components.collision;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;

/**
 * Created by Will on 1/27/2015.
 */
public class BoxFixtureComponent extends FixtureComponent {

    private final PolygonShape shape = new PolygonShape();

    public BoxFixtureComponent(float width, float height, float relativeX, float relativeY, float angle) {

        shape.setAsBox(width, height, new Vec2(relativeX, relativeY), angle);
        fixtureDef.setShape(shape);
    }
}
