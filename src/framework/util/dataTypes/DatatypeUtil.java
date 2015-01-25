package framework.util.dataTypes;

import java.io.IOException;

/**
 * @author William Gervasio
 */

public class DatatypeUtil {

    public static final int INTEGER_SIZE_BYTES = Integer.SIZE / Byte.SIZE;
    public static final int FLOAT_SIZE_BYTES = Float.SIZE / Byte.SIZE;

    public static float parseFloat(String strNumber) throws IOException {

        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Float.parseFloat(strNumber);
            } catch (Exception e) {
                throw new IOException("Float could not be parsed: " + strNumber);
            }
        } else return 0;
    }
}