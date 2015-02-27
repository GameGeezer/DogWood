package framework.util.math;

/**
 * Created by Will on 1/6/2015.
 */
public class ImmutableVector2f extends Vector2f {

    public final float x, y;

    public ImmutableVector2f() {

        this.x = 0;
        this.y = 0;
    }

    public ImmutableVector2f(float x, float y) {

        this.x = x;
        this.y = y;
    }
}
