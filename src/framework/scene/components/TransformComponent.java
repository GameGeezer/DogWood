package framework.scene.components;

import framework.scene.Transform;
import framework.util.math.Matrix4;
import framework.util.math.Quaternion;
import framework.util.math.Vector3;

/**
 * Created by Will on 11/25/2014.
 */
public class TransformComponent extends Transform implements IStaticEntityComponent {
    public TransformComponent() {

    }

    public TransformComponent(Transform transform) {
        setTranslation(transform.getX(), transform.getY(), transform.getZ());
        setScale(transform.getScaleX(), transform.getScaleY(), transform.getScaleZ());
    }
}
