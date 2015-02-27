package framework.util;

/**
 * @author William Gervasio
 */

public final class RangeUtil {

    /**
     * Used to force a value is within a given range
     *
     * @param value
     * @param min
     * @param max
     * @return The new limited value
     */
    public static float forceIntoRange(float value, float min, float max) {

        return value <= max ? value >= min ? value : min : max;
    }

    /**
     * Used to force a value is within a given range
     *
     * @param value
     * @param min
     * @param max
     * @return The new limited value
     */
    public static int forceIntoRange(int value, int min, int max) {

        return value <= max ? value >= min ? value : min : max;
    }

    /**
     * Used to make sure a value is within a given range
     *
     * @param value
     * @param min
     * @param max
     * @return True if the value is within range.
     */
    public static boolean isWithinRange(float value, float min, float max) {

        return value <= max && (value >= min);
    }
}