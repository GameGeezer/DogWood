package framework.util.math;

/**
 * @author William Gervasio
 */
public class Matrix4 implements Cloneable {

    public static int NUMBER_OF_CELLS = 16;

    public static final int  M00 = 0,  M10 = 1,  M20 =2,   M30 =3,
            M01 = 4,  M11 = 5,  M21 = 6,  M31 = 7,
            M02 = 8,  M12 = 9,  M22 = 10, M32 = 11,
            M03 = 12, M13 = 13, M23 = 14, M33 = 15;

    public float[] data = new float[16];

    public Matrix4() {
        loadIdentity();
    }

    public Matrix4(float[][] data) {
        set(data);
    }

    public Matrix4(float[] data) {
        set(data);
    }

    public static void add(Matrix4 left, Matrix4 right, Matrix4 target) {
        target.data[M00] = left.data[M00] + right.data[M00];
        target.data[M10] = left.data[M10] + right.data[M10];
        target.data[M20] = left.data[M20] + right.data[M20];
        target.data[M30] = left.data[M30] + right.data[M30];
        target.data[M01] = left.data[M01] + right.data[M01];
        target.data[M11] = left.data[M11] + right.data[M11];
        target.data[M21] = left.data[M21] + right.data[M21];
        target.data[M31] = left.data[M31] + right.data[M31];
        target.data[M02] = left.data[M02] + right.data[M02];
        target.data[M12] = left.data[M12] + right.data[M12];
        target.data[M22] = left.data[M22] + right.data[M22];
        target.data[M32] = left.data[M32] + right.data[M32];
        target.data[M03] = left.data[M03] + right.data[M03];
        target.data[M13] = left.data[M13] + right.data[M13];
        target.data[M23] = left.data[M23] + right.data[M23];
        target.data[M33] = left.data[M33] + right.data[M33];
    }

    public static void subtract(Matrix4 left, Matrix4 right, Matrix4 target) {
        target.data[M00] = left.data[M00] - right.data[M00];
        target.data[M10] = left.data[M10] - right.data[M10];
        target.data[M20] = left.data[M20] - right.data[M20];
        target.data[M30] = left.data[M30] - right.data[M30];
        target.data[M01] = left.data[M01] - right.data[M01];
        target.data[M11] = left.data[M11] - right.data[M11];
        target.data[M21] = left.data[M21] - right.data[M21];
        target.data[M31] = left.data[M31] - right.data[M31];
        target.data[M02] = left.data[M02] - right.data[M02];
        target.data[M12] = left.data[M12] - right.data[M12];
        target.data[M22] = left.data[M22] - right.data[M22];
        target.data[M32] = left.data[M32] - right.data[M32];
        target.data[M03] = left.data[M03] - right.data[M03];
        target.data[M13] = left.data[M13] - right.data[M13];
        target.data[M23] = left.data[M23] - right.data[M23];
        target.data[M33] = left.data[M33] - right.data[M33];
    }

    public static void multiply(Matrix4 left, Matrix4 right, Matrix4 target) {
        float m00 = left.data[M00] * right.data[M00] +  left.data[M10] * right.data[M01] +  left.data[M20] * right.data[M02] +  left.data[M30] * right.data[M03];
        float m10 = left.data[M00] * right.data[M10] +  left.data[M10] * right.data[M11] +  left.data[M20] * right.data[M12] +  left.data[M30] * right.data[M13];
        float m20 = left.data[M00] * right.data[M20] +  left.data[M10] * right.data[M21] +  left.data[M20] * right.data[M22] +  left.data[M30] * right.data[M23];
        float m30 = left.data[M00] * right.data[M30] +  left.data[M10] * right.data[M31] +  left.data[M20] * right.data[M32] +  left.data[M30] * right.data[M33];

        float m01 = left.data[M01] * right.data[M00] +  left.data[M11] * right.data[M01] +  left.data[M21] * right.data[M02] +  left.data[M31] * right.data[M03];
        float m11 = left.data[M01] * right.data[M10] +  left.data[M11] * right.data[M11] +  left.data[M21] * right.data[M12] +  left.data[M31] * right.data[M13];
        float m21 = left.data[M01] * right.data[M20] +  left.data[M11] * right.data[M21] +  left.data[M21] * right.data[M22] +  left.data[M31] * right.data[M23];
        float m31 = left.data[M01] * right.data[M30] +  left.data[M11] * right.data[M31] +  left.data[M21] * right.data[M32] +  left.data[M31] * right.data[M33];

        float m02 = left.data[M02] * right.data[M00] +  left.data[M12] * right.data[M01] +  left.data[M22] * right.data[M02] +  left.data[M32] * right.data[M03];
        float m12 = left.data[M02] * right.data[M10] +  left.data[M12] * right.data[M11] +  left.data[M22] * right.data[M12] +  left.data[M32] * right.data[M13];
        float m22 = left.data[M02] * right.data[M20] +  left.data[M12] * right.data[M21] +  left.data[M22] * right.data[M22] +  left.data[M32] * right.data[M23];
        float m32 = left.data[M02] * right.data[M30] +  left.data[M12] * right.data[M31] +  left.data[M22] * right.data[M32] +  left.data[M32] * right.data[M33];

        float m03 = left.data[M03] * right.data[M00] +  left.data[M13] * right.data[M01] +  left.data[M23] * right.data[M02] +  left.data[M33] * right.data[M03];
        float m13 = left.data[M03] * right.data[M10] +  left.data[M13] * right.data[M11] +  left.data[M23] * right.data[M12] +  left.data[M33] * right.data[M13];
        float m23 = left.data[M03] * right.data[M20] +  left.data[M13] * right.data[M21] +  left.data[M23] * right.data[M22] +  left.data[M33] * right.data[M23];
        float m33 = left.data[M03] * right.data[M30] +  left.data[M13] * right.data[M31] +  left.data[M23] * right.data[M32] +  left.data[M33] * right.data[M33];

        target.data[M00] = m00;
        target.data[M10] = m10;
        target.data[M20] = m20;
        target.data[M30] = m30;
        target.data[M01] = m01;
        target.data[M11] = m11;
        target.data[M21] = m21;
        target.data[M31] = m31;
        target.data[M02] = m02;
        target.data[M12] = m12;
        target.data[M22] = m22;
        target.data[M32] = m32;
        target.data[M03] = m03;
        target.data[M13] = m13;
        target.data[M23] = m23;
        target.data[M33] = m33;
    }

    /**
     * TODO
     * @param other
     * @return
     */
    public static void divide(Matrix4 other) {


    }

    public Matrix4 set(Matrix4 other){
        data[M00] = other.data[M00]; data[M10] = other.data[M10]; data[M20] = other.data[M20]; data[M30] = other.data[M30];
        data[M01] = other.data[M01]; data[M11] = other.data[M11]; data[M21] = other.data[M21]; data[M31] = other.data[M31];
        data[M02] = other.data[M02]; data[M12] = other.data[M12]; data[M22] = other.data[M22]; data[M32] = other.data[M32];
        data[M03] = other.data[M03]; data[M13] = other.data[M13]; data[M23] = other.data[M23]; data[M33] = other.data[M33];

        return this;
    }

    public Matrix4 set(float[][] data) {
        this.data[M00] = data[0][0]; this.data[M10] = data[1][0];  this.data[M20] = data[2][0]; this.data[M30] = data[3][0];
        this.data[M01] = data[0][1]; this.data[M11] = data[1][1];  this.data[M21] = data[2][1]; this.data[M31] = data[3][1];
        this.data[M02] = data[0][2]; this.data[M12] = data[1][2];  this.data[M22] = data[2][2]; this.data[M32] = data[3][2];
        this.data[M03] = data[0][3]; this.data[M13] = data[1][3];  this.data[M23] = data[2][3]; this.data[M33] = data[3][3];

        return this;
    }

    public Matrix4 set(float[] data) {
        this.data[M00] = data[M00]; this.data[M10] = data[M10]; this.data[M20] = data[M20]; this.data[M30] = data[M30];
        this.data[M01] = data[M01]; this.data[M11] = data[M11]; this.data[M21] = data[M21]; this.data[M31] = data[M31];
        this.data[M02] = data[M02]; this.data[M12] = data[M12]; this.data[M22] = data[M22]; this.data[M32] = data[M32];
        this.data[M03] = data[M03]; this.data[M13] = data[M13]; this.data[M23] = data[M23]; this.data[M33] = data[M33];

        return this;
    }

    public Matrix4 loadIdentity() {
        data[M00] = 1; data[M10] = 0; data[M20] = 0; data[M30] = 0;
        data[M01] = 0; data[M11] = 1; data[M21] = 0; data[M31] = 0;
        data[M02] = 0; data[M12] = 0; data[M22] = 1; data[M32] = 0;
        data[M03] = 0; data[M13] = 0; data[M23] = 0; data[M33] = 1;

        return this;
    }

    public Matrix4 loadZero() {
        data[M00] = 0; data[M10] = 0; data[M20] = 0; data[M30] = 0;
        data[M01] = 0; data[M11] = 0; data[M21] = 0; data[M31] = 0;
        data[M02] = 0; data[M12] = 0; data[M22] = 0; data[M32] = 0;
        data[M03] = 0; data[M13] = 0; data[M23] = 0; data[M33] = 0;

        return this;
    }

    public Matrix4 clone() {
        return new Matrix4(data);
    }

    public String toString() {
        return new String(data[M00] + " " + data[M10] + " " + data[M20] + " " + data[M30] + "\n" +
                          data[M01] + " " + data[M11] + " " + data[M21] + " " + data[M31] + "\n" +
                          data[M02] + " " + data[M12] + " " + data[M22] + " " + data[M32] + "\n" +
                          data[M03] + " " + data[M13] + " " + data[M23] + " " + data[M33]);
    }
}
