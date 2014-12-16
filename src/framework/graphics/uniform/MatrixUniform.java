package framework.graphics.uniform;

import framework.util.math.Matrix4;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

/**
 * Created by Will on 12/12/2014.
 */
public class MatrixUniform extends Uniform {

    public enum MatrixUniformType {
        MATRIX2(4), MATRIX3(9), MATRIX4(16);

        private int numberOfUniforms;

        MatrixUniformType(int numberOfUniforms) {
            this.numberOfUniforms = numberOfUniforms;
        }

        public int getNumberOfUniforms() {
            return numberOfUniforms;
        }
    }

    private MatrixUniformType uniformType;
    private FloatBuffer uniformData;

    public MatrixUniform(String uniformName, MatrixUniformType uniformType) {
        super(uniformName);
        this.uniformType = uniformType;
        uniformData = BufferUtils.createFloatBuffer(uniformType.getNumberOfUniforms());
    }

    public void setUniformData(float... data) {
        uniformData.put(data, 0, uniformType.getNumberOfUniforms());
        uniformData.flip();
        updateListeningShaders();
    }

    public int getNumberOfUniforms() {
        return uniformType.getNumberOfUniforms();
    }

    protected void updateProgram(int uniformHandle) {
        switch (uniformType) {

            case MATRIX2:
                GL20.glUniformMatrix2(uniformHandle, false, uniformData);
                break;
            case MATRIX3:
                GL20.glUniformMatrix3(uniformHandle, false, uniformData);
                break;
            case MATRIX4:
                GL20.glUniformMatrix4(uniformHandle, false, uniformData);
                break;
        }
    }
}
