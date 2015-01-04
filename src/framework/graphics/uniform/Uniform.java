package framework.graphics.uniform;

import framework.graphics.opengl.ShaderProgram;

import java.util.ArrayList;
import java.util.List;

/**
 * Uniforms are wrappers for data that need to be passed to a shader.
 * ShaderPrograms subscribe to uniforms so that reusable data (i.e. the view and projection matrices
 * created by a camera) can be stored in one place notify subscribers when changed.
 *
 * @author William Gervasio
 */
public abstract class Uniform {

    private final List<ShaderProgram> listeners = new ArrayList<ShaderProgram>();
    private final String uniformName;

    /**
     * Create a Uniform object associated with a uniform of passed "uniformName"
     *
     * @param uniformName
     */
    public Uniform(String uniformName) {

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

    /**
     * Remove the shader from the list of ShaderPrograms to be updated when the uniform is changed
     *
     * @param listener
     */
    public void removeListener(ShaderProgram listener) {
        listeners.remove(listener);
    }

    /**
     *
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
     *
     * @param shader the program to be updated
     */
    private void passUniformToShader(ShaderProgram shader) {

        shader.bind();

        // Find the handle to the uniform
        int uniformHandle = shader.getUniformLocation(uniformName);
        // Using the handle update the program
        updateProgram(uniformHandle);

        shader.unbind();
    }
}
