package framework.graphics.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.HashMap;
import java.util.Map;

/**
 * The VAO (Vertex Array Object) wraps an FBO and IBO and uses vertex attribute pointers to describe
 * how data in the FBO should be passed to the shader.
 *
 * @author William Gervasio
 */
public final class VAO {

    private final Map<Integer, Descriptor> descriptors = new HashMap<Integer, Descriptor>();

    private final FBO fbo;
    private final IBO ibo;
    private final int handle;
    private final int size;

    public VAO(FBO FBO, IBO ibo, int size) {

        this.fbo = FBO;
        this.ibo = ibo;
        this.size = size;

        handle = GL30.glGenVertexArrays();
    }

    /**
     * Using passed IVertexAttributes
     */
    public final void init() {

        GL30.glBindVertexArray(handle);

        fbo.bind();
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
    }

    /**
     *
     * @param index The position of the attribute in the vertex shader
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
}