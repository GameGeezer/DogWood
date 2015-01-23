package framework.graphics.uniform;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

/**
 * @author William Gervasio
 */
public class FloatVectorUniform extends VectorUniform {

    private final FloatBuffer uniformData;

    public FloatVectorUniform(String uniformName, VectorUniformType uniformType) {

        super(uniformName, uniformType);

        uniformData = BufferUtils.createFloatBuffer(uniformType.getNumberOfUniforms());
    }

    public final void setUniformData(float... data) {

        uniformData.put(data, 0, uniformType.getNumberOfUniforms());
        uniformData.flip();

        updateListeningShaders();
    }

    @Override
    protected final void updateProgram(int handle) {

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
