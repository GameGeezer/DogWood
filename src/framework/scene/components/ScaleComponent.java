package framework.scene.components;

import framework.util.math.Vector3;

/**
 * @author William Gervasio
 */
public class ScaleComponent extends Vector3 implements StaticEntityComponent {

    public ScaleComponent() {
        super(0, 0, 0);
    }

    public ScaleComponent(float x, float y, float z) {
        super(x, y, z);
    }
}