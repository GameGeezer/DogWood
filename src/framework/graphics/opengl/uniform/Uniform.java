package framework.graphics.opengl.uniform;

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
@SuppressWarnings("WeakerAccess")
public abstract class Uniform {

    private final List<ShaderProgram> listeners = new ArrayList<>();
    private final String uniformName;

    /**
     * Create a Uniform object associated with a uniform of passed "uniformName"
     *
     * @param uniformName The name of the uniform in the shader
     */
    public Uniform(String uniformName) {

        this.uniformName = uniformName;
    }

    /**
     * Subscribe to the uniform. If the uniform is changed all listeners will be updated
     *
     * @param listener The shader that wishes to subscribe to the uniform
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
     * @param listener The shader that wishes to be removed from the uniform
     */
    public void removeListener(ShaderProgram listener) {

        listeners.remove(listener);
    }

    protected void updateListeningShaders() {

        listeners.forEach(this::passUniformToShader);
    }

    /**
     * Update the uniform for the program. This is specific for each type of uniform data. The shader that
     * needs an update must be bound before this is called.
     *
     * @param uniformHandle The handle to this uniform in the shader program
     */
    protected abstract void updateProgram(int uniformHandle);

    /**
     * Update the shader uniform
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
