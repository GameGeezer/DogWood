package framework.graphics.uniform;

import framework.graphics.opengl.ShaderProgram;

/**
 * Created by Will on 1/4/2015.
 */
public interface IUniformWrapper {

    public abstract void addListener(ShaderProgram shader);

    public abstract void removeListener(ShaderProgram shader);
}
