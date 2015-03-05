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

    public float r, g, b, a;

    public Color() {

        this(MINIMUM_CHANNEL_VALUE, MINIMUM_CHANNEL_VALUE, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);
    }

    public Color(final float r, final float g, final float b, final float a) {

        set(r, g, b, a);
    }

    public Color(final long hex) {

        set(hex);
    }

    public Color(final Color color) {

        this(color.r, color.g, color.b, color.a);
    }

    /**
     * Increase r, g, b by a percentage.
     *
     * @param percentage The percentage must be in decimal form on the interval of [0, 1]
     * @return This object
     */
    public final Color brighten(final float percentage) {

        final float brightenPercentage = 1.0f + percentage;
        setRed(r * brightenPercentage);
        setGreen(g * brightenPercentage);
        setBlue(b * brightenPercentage);
        return this;
    }

    /**
     * Decrease r, g, b by a percentage. The percentage must be in decimal form on the interval of [0, 1]
     *
     * @param percentage The percentage must be in decimal form on the interval of [0, 1]
     * @return This object
     */
    public final Color darken(final float percentage) {

        final float darkenPercentage = 1.0f - percentage;
        setRed(r * darkenPercentage);
        setGreen(g * darkenPercentage);
        setBlue(b * darkenPercentage);
        
        return this;
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

        setRed(r);
        setGreen(g);
        setBlue(b);
        setAlpha(a);

        return this;
    }

    public final Color set(final long hex) {

        a = (hex & 0xFF000000L) >> 24;
        r = (hex & 0xFF0000L) >> 16;
        g = (hex & 0xFF00L) >> 8;
        b = (hex & 0xFFL);

        return this;
    }

    public final Color setRed(final float r) {

        this.r = RangeUtil.forceIntoRange(r, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);

        return this;
    }

    public final Color setGreen(final float g) {

        this.g = RangeUtil.forceIntoRange(g, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);

        return this;
    }

    public final Color setBlue(final float b) {

        this.b = RangeUtil.forceIntoRange(b, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);

        return this;
    }

    public final Color setAlpha(final float a) {

        this.a = RangeUtil.forceIntoRange(a, MINIMUM_CHANNEL_VALUE, MAXIMUM_CHANNEL_VALUE);

        return this;
    }


    /**
     * Creates a float[] of r, g, b, a
     *
     * @return The r,g, b, a values as a float array
     */
    public final float[] getElements() {

        return new float[]{r, g, b, a};
    }

}
