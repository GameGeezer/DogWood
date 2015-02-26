package framework.graphics.opengl.bufferObjects;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL31;

/**
 * TODO
 * @author William Gervasio
 */
public class UBO {

    private final int handle;

    public UBO() {

        handle =  GL15.glGenBuffers();
    }

    public void bind() {

        GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, handle);
    }

    public void unbind() {

        GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0);
    }

    public void destroy() {


    }
}
