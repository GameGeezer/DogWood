package framework.graphics.uniform;

import framework.graphics.opengl.ShaderProgram;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 12/10/2014.
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
