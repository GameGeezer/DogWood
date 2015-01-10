package framework.graphics.uniform;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

/**
 * @author William Gervasio
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

    @Override
    public int getNumberOfElements() {

        return uniformType.getNumberOfUniforms();
    }

    @Override
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
