package framework.graphics.uniform;

import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.UBO;
import framework.util.dataTypes.FloatArrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 1/23/2015.
 */
public abstract class UniformBlock {

    private final List<ShaderProgram> listeners = new ArrayList<ShaderProgram>();
    private final String uniformName;

    private UBO ubo;

    public UniformBlock(String uniformName) {

        this.uniformName = uniformName;
    }

    /**
     * Subscribe to the uniform. If the uniform is changed all listeners will be updated
     *
     * @param listener
     */
    public void addListener(ShaderProgram listener) {

        // Passes the uniform data to the shader
        passUniformToShader(listener);
        // Subscribe the shader for future updates
        listeners.add(listener);
    }

    public void removeListener(ShaderProgram listener) {
        listeners.remove(listener);
    }

    /**
     * @return the number of elements in the uniform
     */
    public abstract int getNumberOfElements();

    protected void updateListeningShaders() {

        listeners.forEach((shader) -> passUniformToShader(shader));
    }

    /**
     * Update the uniform for the program. This is specific for each type of uniform data. The shader that
     * needs an update must be bound before this is called.
     *
     * @param uniformHandle
     */
    protected abstract void updateProgram(int uniformHandle);

    /**
     * @param shader the program to be updated
     */
    private void passUniformToShader(ShaderProgram shader) {

        shader.bind();

        // Find the handle to the uniform
        int uniformHandle = shader.getUniformBlockLocation(uniformName);
        // Using the handle update the program
        updateProgram(uniformHandle);

        shader.unbind();
    }
}
