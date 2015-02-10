package framework.graphics.opengl.uniform;

import framework.graphics.opengl.ShaderProgram;

/**
 * @author William Gervasio
 */
public interface IUniformWrapper {

    public abstract void addListener(ShaderProgram shader);

    public abstract void removeListener(ShaderProgram shader);
}
