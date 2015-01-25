package framework.graphics;

import framework.graphics.opengl.*;
import framework.graphics.opengl.bufferObjects.BufferedObjectUsage;
import framework.graphics.opengl.bufferObjects.IBO;
import framework.graphics.opengl.bufferObjects.VBO;
import framework.graphics.vertices.VertexAttribute;
import framework.util.dataTypes.DatatypeUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

/**
 * Given an array if indices and and a List of IVertexAttribues a Mesh generates a VAO.
 *
 * @author william gervasio
 */

public class Mesh {

    protected final VAO vao;

    private final List<VertexAttribute> vertexElements;


    /**
     * @param indices
     * @param vertexElements
     */
    public Mesh(int[] indices, List<VertexAttribute> vertexElements) {

        this.vertexElements = vertexElements;

        //create vertex buffer (VBO)
        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(countTotalElements());
        for (VertexAttribute element : vertexElements) {
            verticesBuffer.put(element.getData());
        }
        verticesBuffer.flip();
        final VBO vbo = new VBO(verticesBuffer, BufferedObjectUsage.STATIC_DRAW);

        // Create the Index Buffer (IBO)
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices);
        indicesBuffer.flip();
        final IBO ibo = new IBO(indicesBuffer, BufferedObjectUsage.STATIC_DRAW);

        // Create the Vertex Array Object (VAO)
        vao = new VAO(vbo, ibo, indices.length);
        int offset = 0;
        int i = 0;

        for (VertexAttribute element : vertexElements) {
            // Find the number of bytes per element for the attribute
            final int sizeInBytes = element.getElementsPerVertex() * DatatypeUtil.FLOAT_SIZE_BYTES;
            // Find the number of vertices contained within the attribute data
            final int numberOfVertices = element.getData().length / element.getElementsPerVertex();
            vao.addVertexAttribute(i, new Descriptor(element.getElementsPerVertex(), GL11.GL_FLOAT, false, sizeInBytes, offset));
            offset += sizeInBytes * numberOfVertices;
            ++i;
        }

        vao.init();
    }

    /**
     * Draw the mesh
     */
    public void draw() {

        vao.draw();
    }

    /**
     * Destroy the VAO
     */
    public void destroy() {

        vao.destroy();
    }

    private int countTotalElements() {

        int totalElementsCount = 0;

        for (VertexAttribute element : vertexElements) {

            totalElementsCount += element.getData().length;
        }

        return totalElementsCount;
    }
}
