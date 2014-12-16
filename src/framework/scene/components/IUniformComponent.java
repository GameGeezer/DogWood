package framework.scene.components;

import framework.graphics.opengl.ShaderProgram;

/**
 * Created by Will on 12/16/2014.
 */
public interface IUniformComponent  extends IComponentType {

    public abstract void addListener(ShaderProgram shader);

    public abstract void removeListener(ShaderProgram shader);
}
