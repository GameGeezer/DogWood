package framework.graphics.opengl;

import framework.graphics.opengl.bufferObjects.IBO;
import framework.graphics.opengl.bufferObjects.VBO;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.HashMap;
import java.util.Map;

/**
 * The VAO (Vertex Array Object) wraps an VBO and IBO and uses vertex attribute pointers to describe
 * how data in the VBO should be passed to the shader.
 *
 * @author William Gervasio
 */
public final class VAO {

    private final Map<Integer, Descriptor> descriptors = new HashMap<>();

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

    /**
     * Using passed IVertexAttributes
     */
    public final void init() {

        GL30.glBindVertexArray(handle);

        vbo.bind();
        ibo.bind();

        for (int i : descriptors.keySet()) {

            final Descriptor descriptor = descriptors.get(i);

            // For this VAO assign an attribute at position i in the vertex shader
            GL20.glEnableVertexAttribArray(i);
            // Describe the data at position i
            GL20.glVertexAttribPointer(i, descriptor.getSize(),
                    descriptor.getType(), descriptor.isNormalized(),
                    descriptor.getStride(), descriptor.getPointer());

        }


        GL30.glBindVertexArray(0);

        vbo.unbind();
        ibo.unbind();
    }

    /**
     * @param index      The position of the attribute in the vertex shader
     * @param descriptor How the data is formatted
     */
    public final void addVertexAttribute(final int index, final Descriptor descriptor) {

        descriptors.put(index, descriptor);
    }

    /**
     * Draws the elements as triangles
     */
    public final void draw() {

        GL30.glBindVertexArray(handle);

        GL11.glDrawElements(GL11.GL_TRIANGLES, size, GL11.GL_UNSIGNED_INT, 0);

        GL30.glBindVertexArray(0);
    }

    /**
     * Delete the VertexArray and it's corresponding VBO and IBO
     */
    public final void destroy() {

        // Delete the vertex array
        GL30.glDeleteVertexArrays(handle);

        vbo.destroy();

        ibo.destroy();
    }
}