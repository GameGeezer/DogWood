package game;

import framework.graphics.opengl.ShaderProgram;

/**
 * Created by Will on 12/15/2014.
 */
public interface IUniformWrapper {

    public abstract void addListener(ShaderProgram shader);

    public abstract void removeListener(ShaderProgram shader);
}
