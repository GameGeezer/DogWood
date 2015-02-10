package framework.graphics;


import framework.util.RangeUtil;

/**
 * A color object is used to store r, g, b, a values as floats on the interval of [0, 1]
 *
 * @author William Gervasio
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue", "UnusedDeclaration"})
public final class Color implements Cloneable {

    private static final float MINIMUM_CHANNEL_VALUE = 0.0f;
    private static final float MAXIMUM_CHANNEL_VALUE = 1.0f;

    private float r, g, b, a;

    public Color() {

        this(MINIMUM_CHANNEL_VALUE, MINIMUM_CHANNEL_VALUE, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);
    }

    public Color(float r, float g, float b, float a) {
        
        set(r, g, b, a);
    }

    /**
     * Increase r, g, b by a percentage.
     *
     * @param percentage The percentage must be in decimal form on the interval of [0, 1]
     * @return This object
     */
    public final Color brighten(float percentage) {

        percentage += 1;

        r *= percentage;
        g *= percentage;
        b *= percentage;

        return clamp();
    }

    /**
     * Decrease r, g, b by a percentage. The percentage must be in decimal form on the interval of [0, 1]
     *
     * @param percentage The percentage must be in decimal form on the interval of [0, 1]
     * @return This object
     */
    public final Color darken(float percentage) {

        percentage = 1 - percentage;

        r *= percentage;
        g *= percentage;
        b *= percentage;

        return clamp();
    }

    /**
     * Set r, g, b, a values to the passed values
     *
     * @param r The red channel value on the interval of [0, 1]
     * @param g The green channel value on the interval of [0, 1]
     * @param b The blue channel value on the interval of [0, 1]
     * @param a The alpha channel value on the interval of [0, 1]
     * @return This object
     */
    public final Color set(final float r, final float g, final float b, final float a) {

        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;

        return clamp();
    }

    public final Color setRed(float r) {

        this.r = RangeUtil.forceIntoRange(r, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);

        return this;
    }

    public final float getRed() {

        return r;
    }

    public final Color setGreen(float g) {

        this.g = RangeUtil.forceIntoRange(g, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);

        return this;
    }

    public final float getGreen() {

        return g;
    }

    public final Color setBlue(float b) {

        this.b = RangeUtil.forceIntoRange(b, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);

        return this;
    }

    public final float getBlue() {

        return b;
    }

    public final Color setAlpha(float a) {

        this.a = RangeUtil.forceIntoRange(a, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);

        return this;
    }

    public final float getAlpha() {

        return a;
    }

    /**
     * Creates a float[] of r, g, b, a
     *
     * @return The r,g, b, a values as a float array
     */
    public final float[] getElements() {

        return new float[]{r, g, b, a};
    }

    /**
     * Forces r, g, b, a float values within the desired range of [0, 1]
     *
     * @return This object
     */
    private final Color clamp() {

        r = RangeUtil.forceIntoRange(r, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);
        g = RangeUtil.forceIntoRange(g, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);
        b = RangeUtil.forceIntoRange(b, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);
        a = RangeUtil.forceIntoRange(a, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);

        return this;
    }

    public final Color clone() {

        return new Color(r, g, b, a);
    }
}
