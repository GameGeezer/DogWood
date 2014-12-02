package framework.util.math;

/**
 * @author William Gervasio
 */
public class Quaternion {

    float x, y, z, w = 0f;

    public Quaternion() {

    }

    public float length2() {
        return x * x + y * y + z * z + w * w;
    }

    public float length() {
        return (float) Math.sqrt(length2());
    }

    public void inverse() {
        float length = length2();
        x /= length;
        y /= length;
        z /= length;
        w /= length;
    }

    public Matrix4 getMatrix() {

        float sqx = x * x;
        float sqy = y * y;
        float sqz = z * z;
        float sqw = w * w;

        float inverseSquare = 1 / length2();

        Matrix4 matrix = new Matrix4();

        matrix.data[Matrix4.M00] = ( sqx - sqy -sqz + sqw) * inverseSquare;
        matrix.data[Matrix4.M11] = (-sqx + sqy -sqz + sqw) * inverseSquare;
        matrix.data[Matrix4.M22] = (-sqx - sqy +sqz + sqw) * inverseSquare;

        float temp1 = x * y;
        float temp2 = z * w;
        matrix.data[Matrix4.M10] = 2 * (temp1 + temp2) / inverseSquare;
        matrix.data[Matrix4.M01] = 2 * (temp1 - temp2) / inverseSquare;

        temp1 = x * z;
        temp2 = y * w;
        matrix.data[Matrix4.M20] = 2 * (temp1 + temp2) / inverseSquare;
        matrix.data[Matrix4.M02] = 2 * (temp1 - temp2) / inverseSquare;

        temp1 = y * z;
        temp2 = x * w;
        matrix.data[Matrix4.M30] = 2 * (temp1 + temp2) / inverseSquare;
        matrix.data[Matrix4.M03] = 2 * (temp1 - temp2) / inverseSquare;

        return matrix;
    }
}
