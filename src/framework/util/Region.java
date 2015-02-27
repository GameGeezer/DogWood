package framework.util;

import framework.util.math.Vector2f;

/**
 * Created by Will on 12/23/2014.
 */
public class Region {

    private final Vector2f lower, upper;
    private float width, height;

    public Region(float x1, float y1, float x2, float y2) {

        lower = new Vector2f(x1, y1);
        upper = new Vector2f(x2, y2);

        ensureStateLegalityX();
        ensureStateLegalityY();

        updateBounds();
    }

    public Region setLower(float x1, float y1) {

        lower.set(x1, y1);

        ensureStateLegalityX();
        ensureStateLegalityY();

        updateBounds();

        return this;
    }

    public Region setLowerX(float x) {

        lower.x = x;

        ensureStateLegalityX();

        updateBounds();

        return this;
    }

    public Region setLowerY(float y) {

        lower.y = y;

        ensureStateLegalityY();

        updateBounds();

        return this;
    }

    public Region setUpper(float x2, float y2) {

        upper.set(x2, y2);

        ensureStateLegalityX();
        ensureStateLegalityY();

        updateBounds();

        return this;
    }

    public Region setUpperX(float x) {

        upper.x = x;

        ensureStateLegalityX();

        updateBounds();

        return this;
    }

    public Region setUpperY(float y) {

        upper.y = y;

        ensureStateLegalityY();

        updateBounds();

        return this;
    }

    public Region shift(float x, float y) {

        lower.add(x, y);
        upper.add(x, y);

        return this;
    }

    public float getLowerX() {

        return lower.x;
    }

    public float getLowerY() {

        return lower.y;
    }

    public float getUpperX() {

        return upper.x;
    }

    public float getUpperY() {

        return upper.y;
    }

    public float findCenterX() {

        return lower.x + (width / 2.0f);
    }

    public float findCenterY() {

        return lower.y + (height / 2.0f);
    }

    public float getWidth() {

        return width;
    }

    public float getHeight() {

        return height;
    }

    public Vector2f getLower() {

        return lower;
    }

    public Vector2f getUpper() {

        return upper;
    }

    private void updateBounds() {

        width = Math.abs(upper.x - lower.x);

        height = Math.abs(upper.y - lower.y);
    }

    private void ensureStateLegalityX() {

        if (getLowerX() > getUpperX()) {

            float x = getLowerX();
            lower.x = upper.x;
            upper.x = x;
        }
    }

    private void ensureStateLegalityY() {

        if (lower.y > upper.y) {

            float y = lower.y;
            lower.y = upper.y;
            upper.y = y;
        }
    }
}
