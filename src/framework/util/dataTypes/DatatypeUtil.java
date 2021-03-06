package framework.util.dataTypes;

/**
 * @author William Gervasio
 */
public class DatatypeUtil {

    public static final int INTEGER_SIZE_BYTES = Integer.SIZE / Byte.SIZE;
    public static final int FLOAT_SIZE_BYTES = Float.SIZE / Byte.SIZE;

    public static float parseFloat(final String strNumber) throws NumberFormatException {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Float.parseFloat(strNumber);
            } catch (final NumberFormatException e) {
                throw new NumberFormatException("Float could not be parsed: " + strNumber);
            }
        } else {
            return 0.0f;
        }
    }
}
