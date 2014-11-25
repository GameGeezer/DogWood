package framework.scene.components;

import framework.util.math.Vector3;

/**
 * Created by Will on 5/5/14.
 */
public class PositionComponent extends Vector3 implements StaticEntityComponent {

    public PositionComponent() {
        super(0, 0, 0);
    }

    public PositionComponent(float x, float y, float z) {
        super(x, y, z);
    }
}
