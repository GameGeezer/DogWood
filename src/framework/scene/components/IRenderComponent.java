package framework.scene.components;

import framework.graphics.opengl.ShaderProgram;

/**
 * Created by Will on 12/16/2014.
 */
public interface IRenderComponent  extends IComponentType {

    public abstract void render(int delta);

    public abstract ShaderProgram getShader();
}
