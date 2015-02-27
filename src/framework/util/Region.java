package framework.util;

import framework.util.math.Vector2f;

/**
 * @author William Gervasio
 */
public class Region {

    private final Vector2f lower, upper;
    private float width, height;

    public Region(final float x1, final float y1, final float x2, final float y2) {

        lower = new Vector2f(x1, y1);
        upper = new Vector2f(x2, y2);

        ensureStateLegalityX();
        ensureStateLegalityY();

        updateBounds();
    }

    public final Region setLower(final float x1, final float y1) {

        lower.set(x1, y1);

        ensureStateLegalityX();
        ensureStateLegalityY();

        updateBounds();

        return this;
    }

    public final Region setLowerX(final float x) {

        lower.x = x;

        ensureStateLegalityX();

        updateBounds();

        return this;
    }

    public final Region setLowerY(final float y) {

        lower.y = y;

        ensureStateLegalityY();

        updateBounds();

        return this;
    }

    public final Region setUpper(final float x2,final  float y2) {

        upper.set(x2, y2);

        ensureStateLegalityX();
        ensureStateLegalityY();

        updateBounds();

        return this;
    }

    public final Region setUpperX(final float x) {

        upper.x = x;

        ensureStateLegalityX();

        updateBounds();

        return this;
    }

    public final Region setUpperY(final float y) {

        upper.y = y;

        ensureStateLegalityY();

        updateBounds();

        return this;
    }

    public final Region shift(final float x, final float y) {

        lower.add(x, y);
        upper.add(x, y);

        return this;
    }

    public final float getLowerX() {

        return lower.x;
    }

    public final float getLowerY() {

        return lower.y;
    }

    public final float getUpperX() {

        return upper.x;
    }

    public final float getUpperY() {

        return upper.y;
    }

    public final float findCenterX() {

        return lower.x + (width / 2.0f);
    }

    public final float findCenterY() {

        return lower.y + (height / 2.0f);
    }

    public final float getWidth() {

        return width;
    }

    public final float getHeight() {

        return height;
    }

    private void updateBounds() {

        width = Math.abs(upper.x - lower.x);

        height = Math.abs(upper.y - lower.y);
    }

    private void ensureStateLegalityX() {

        if (getLowerX() > getUpperX()) {

            final float x = getLowerX();
            lower.x = upper.x;
            upper.x = x;
        }
    }

    private void ensureStateLegalityY() {

        if (lower.y > upper.y) {

            final float y = lower.y;
            lower.y = upper.y;
            upper.y = y;
        }
    }
}
