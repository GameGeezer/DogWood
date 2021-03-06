package framework.graphics.opengl.uniform;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.IntBuffer;

/**
 * @author William Gervasio
 */
@SuppressWarnings("UnusedDeclaration")
public final class IntVectorUniform extends VectorUniform {

    private final IntBuffer uniformData;

    public IntVectorUniform(String uniformName, VectorUniform.VectorUniformType uniformType) {

        super(uniformName, uniformType);

        uniformData = BufferUtils.createIntBuffer(uniformType.getNumberOfUniforms());
    }

    public final void setUniformData(int... data) {

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
