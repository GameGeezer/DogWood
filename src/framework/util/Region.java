package framework.util;

import framework.util.math.Vector2;

/**
 * Created by Will on 12/23/2014.
 */
public class Region {

    private Vector2 lower, upper;
    private float width, height;

    public Region(float x1, float y1, float x2, float y2) {

        lower = new Vector2(x1, y1);
        upper = new Vector2(x2, y2);

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

        lower.setX(x);

        ensureStateLegalityX();

        updateBounds();

        return this;
    }

    public Region setLowerY(float y) {

        lower.setY(y);

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

        upper.setX(x);

        ensureStateLegalityX();

        updateBounds();

        return this;
    }

    public Region setUpperY(float y) {

        upper.setY(y);

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

        return lower.getX();
    }

    public float getLowerY() {

        return lower.getY();
    }

    public float getUpperX() {

        return upper.getX();
    }

    public float getUpperY() {

        return upper.getY();
    }

    public float findCenterX() {

        return lower.getX() + (width / 2);
    }

    public float findCenterY() {

        return lower.getY() + (height / 2);
    }

    public float getWidth() {

        return width;
    }

    public float getHeight() {

        return height;
    }

    public Vector2 getLower() {

        return lower;
    }

    public Vector2 getUpper() {

        return upper;
    }

    private void updateBounds() {

        width = Math.abs(upper.getX() - lower.getX());

        height = Math.abs(upper.getY() - lower.getY());
    }

    private void ensureStateLegalityX() {

        if (getLowerX() > getUpperX()) {

            float x = getLowerX();
            lower.setX(getUpperX());
            upper.setX(x);
        }
    }

    private void ensureStateLegalityY() {

        if (lower.getY() > upper.getY()) {

            float y = lower.getY();
            lower.setY(upper.getY());
            upper.setY(y);
        }
    }
}
