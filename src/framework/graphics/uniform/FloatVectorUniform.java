package framework.graphics.uniform;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

/**
 * Created by Will on 12/12/2014.
 */
public class FloatVectorUniform extends VectorUniform {

    private FloatBuffer uniformData = FloatBuffer.allocate(uniformType.getNumberOfUniforms());

    public FloatVectorUniform(String uniformName, VectorUniformType uniformType) {
        super(uniformName, uniformType);
    }

    public void setUniformData(float... data) {
        uniformData.put(data, 0, uniformType.getNumberOfUniforms());
        uniformData.flip();
        updateListeningShaders();
        uniformData = BufferUtils.createFloatBuffer(uniformType.getNumberOfUniforms());
    }

    protected void updateProgram(int handle) {

        switch (uniformType) {
            case VECTOR1:
                GL20.glUniform1(handle, uniformData);
                break;
            case VECTOR2:
                GL20.glUniform2(handle, uniformData);
                break;
            case VECTOR3:
                GL20.glUniform3(handle, uniformData);
                break;
            case VECTOR4:
                GL20.glUniform4(handle, uniformData);
        }
    }
}
