package framework.util.math;

/**
 * Created by Will on 1/6/2015.
 */
public class ImmutableVector2 extends Vector2 {

    private final float x, y;

    public ImmutableVector2() {

        this.x = 0;
        this.y = 0;
    }

    public ImmutableVector2(float x, float y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {

        return x;
    }

    @Override
    public float getY() {

        return y;
    }
}
