package framework.graphics.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.HashMap;
import java.util.Map;

/**
 * @author William Gervasio
 */

public class VAO {
    private final Map<Integer, Descriptor> descriptors = new HashMap<Integer, Descriptor>();

    private final VBO vbo;
    private final IBO ibo;
    private final int handle;
    private final int size;

    public VAO(VBO vbo, IBO ibo, int size) {
        this.vbo = vbo;
        this.ibo = ibo;
        this.size = size;

        handle = GL30.glGenVertexArrays();
    }

    public void init() {
        GL30.glBindVertexArray(handle);

        vbo.bind();

        for (int i : descriptors.keySet()) {

            final Descriptor descriptor = descriptors.get(i);

            GL20.glEnableVertexAttribArray(i);
            GL20.glVertexAttribPointer(i, descriptor.getSize(),
                    descriptor.getType(), descriptor.isNormalized(),
                    descriptor.getStride(), descriptor.getPointer());

        }
        ibo.bind();
        GL30.glBindVertexArray(0);
    }

    public final void addVertexAttribute(final int index, final Descriptor descriptor) {
        descriptors.put(index, descriptor);
    }

    public void draw() {
        GL30.glBindVertexArray(handle);
        GL11.glDrawElements(GL11.GL_TRIANGLES, size, GL11.GL_UNSIGNED_INT, 0);
    }
}