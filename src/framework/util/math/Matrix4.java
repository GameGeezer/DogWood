package framework.util.math;

/**
 * @author William Gervasio
 */
public class Matrix4 implements Cloneable {

    private static final Matrix4 IDENTITY = new Matrix4(new float[]{
           1, 0, 0, 0,
           0, 1, 0, 0,
           0, 0, 1, 0,
           0, 0, 0, 1});

    private static final Matrix4 ZERO = new Matrix4(new float[]{
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0});

    private float[] matrix = new float[16];

    public static final int M00 = 0,  M10 = 1,  M20 =2,   M30 =3,
                             M01 = 4,  M11 = 5,  M21 = 6,  M31 = 7,
                             M02 = 8,  M12 = 9,  M22 = 10, M32 = 11,
                             M03 = 12, M13 = 13, M23 = 14, M33 = 15;

    public Matrix4() {
        set(ZERO);
    }

    public Matrix4(float[][] data) {
        set(data);
    }

    public Matrix4(float[] data) {
        set(data);
    }

    public Matrix4 add(Matrix4 other) {
        matrix[M00] += other.matrix[M00]; matrix[M10] += other.matrix[M10]; matrix[M20] += other.matrix[M20]; matrix[M30] += other.matrix[M30];
        matrix[M01] += other.matrix[M01]; matrix[M11] += other.matrix[M11]; matrix[M21] += other.matrix[M21]; matrix[M31] += other.matrix[M31];
        matrix[M02] += other.matrix[M02]; matrix[M12] += other.matrix[M12]; matrix[M22] += other.matrix[M22]; matrix[M32] += other.matrix[M32];
        matrix[M03] += other.matrix[M03]; matrix[M13] += other.matrix[M13]; matrix[M23] += other.matrix[M23]; matrix[M33] += other.matrix[M33];

        return this;
    }

    public Matrix4 sub(Matrix4 other) {
        matrix[M00] -= other.matrix[M00]; matrix[M10] -= other.matrix[M10]; matrix[M20] -= other.matrix[M20]; matrix[M30] -= other.matrix[M30];
        matrix[M01] -= other.matrix[M01]; matrix[M11] -= other.matrix[M11]; matrix[M21] -= other.matrix[M21]; matrix[M31] -= other.matrix[M31];
        matrix[M02] -= other.matrix[M02]; matrix[M12] -= other.matrix[M12]; matrix[M22] -= other.matrix[M22]; matrix[M32] -= other.matrix[M32];
        matrix[M03] -= other.matrix[M03]; matrix[M13] -= other.matrix[M13]; matrix[M23] -= other.matrix[M23]; matrix[M33] -= other.matrix[M33];

        return this;
    }

    public Matrix4 mul(Matrix4 other) {
        return this;
    }

    public Matrix4 div(Matrix4 other) {
        return this;
    }

    public Matrix4 set(Matrix4 other){
        matrix[M00] = other.matrix[M00]; matrix[M10] = other.matrix[M10]; matrix[M20] = other.matrix[M20]; matrix[M30] = other.matrix[M30];
        matrix[M01] = other.matrix[M01]; matrix[M11] = other.matrix[M11]; matrix[M21] = other.matrix[M21]; matrix[M31] = other.matrix[M31];
        matrix[M02] = other.matrix[M02]; matrix[M12] = other.matrix[M12]; matrix[M22] = other.matrix[M22]; matrix[M32] = other.matrix[M32];
        matrix[M03] = other.matrix[M03]; matrix[M13] = other.matrix[M13]; matrix[M23] = other.matrix[M23]; matrix[M33] = other.matrix[M33];

        return this;
    }

    public Matrix4 set(float[][] data) {
        matrix[M00] = data[0][0]; matrix[M10] = data[1][0];  matrix[M20] = data[2][0]; matrix[M30] = data[3][0];
        matrix[M01] = data[0][1]; matrix[M11] = data[1][1];  matrix[M21] = data[2][1]; matrix[M31] = data[3][1];
        matrix[M02] = data[0][2]; matrix[M12] = data[1][2];  matrix[M22] = data[2][2]; matrix[M32] = data[3][2];
        matrix[M03] = data[0][3]; matrix[M13] = data[1][3];  matrix[M23] = data[2][3]; matrix[M33] = data[3][3];

        return this;
    }

    public Matrix4 set(float[] data) {
        matrix[M00] = data[M00]; matrix[M10] = data[M10]; matrix[M20] = data[M20]; matrix[M30] = data[M30];
        matrix[M01] = data[M01]; matrix[M11] = data[M11]; matrix[M21] = data[M21]; matrix[M31] = data[M31];
        matrix[M02] = data[M02]; matrix[M12] = data[M12]; matrix[M22] = data[M22]; matrix[M32] = data[M32];
        matrix[M03] = data[M03]; matrix[M13] = data[M13]; matrix[M23] = data[M23]; matrix[M33] = data[M33];

        return this;
    }

    public float getCell(int cell) {
        return matrix[cell];
    }

    public Matrix4 clone() {
        return new Matrix4(matrix);
    }
}
