package framework.graphics.uniform;

import framework.graphics.opengl.ShaderProgram;

import java.util.ArrayList;
import java.util.List;

/**
 * Uniforms are wrappers for data that need to be passed to a shader.
 * ShaderPrograms subscribe to uniforms so that reusable data (i.e. the view and projection matrices
 * created by a camera) can be stored in one place and be notified on its change.
 *
 * @author William Gervasio
 */
public abstract class Uniform {

    private final List<ShaderProgram> listeners = new ArrayList<ShaderProgram>();
    private final String uniformName;

    public Uniform(String uniformName) {
        this.uniformName = uniformName;
    }

    /**
     * Subscribe to the uniform. If the uniform is changed all listeners will be updated
     * @param listener
     */
    public void addListener(ShaderProgram listener) {
        passUniformToShader(listener);

        listeners.add(listener);
    }

    /**
     * Remove teh shader from the list of Shaders to be updated when the uniform is changed.
     * @param listener
     */
    public void removeListener(ShaderProgram listener) {
        listeners.remove(listener);
    }

    protected void updateListeningShaders() {
        for(ShaderProgram shader : listeners) {
            passUniformToShader(shader);
        }
    }

    public abstract int getNumberOfUniforms();

    protected abstract void updateProgram(int uniformHandle);

    private void passUniformToShader(ShaderProgram shader) {
        shader.bind();
        int uniformHandle = shader.getUniformLocation(uniformName);
        updateProgram(uniformHandle);
        shader.unbind();
    }
}
