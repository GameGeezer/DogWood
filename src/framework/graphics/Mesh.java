package framework.graphics;

import framework.graphics.opengl.*;
import framework.graphics.vertices.IVertexAttribute;
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

    protected VAO vao;

    private List<IVertexAttribute> vertexElements;
    private int[] indices;

    /**
     *
     * @param indices
     * @param vertexElements
     */
    public Mesh(int[] indices, List<IVertexAttribute> vertexElements) {
        this.indices = indices;
        this.vertexElements = vertexElements;

        //create Vertex Buffer
        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(countTotalElements());
        for (IVertexAttribute element : vertexElements) {
            verticesBuffer.put(element.getData());
        }
        verticesBuffer.flip();
        FBO fbo = new FBO(verticesBuffer, BufferedObjectUsage.STATIC_DRAW);

        //create indexBuffer
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices);
        indicesBuffer.flip();
        IBO ibo = new IBO(indicesBuffer, BufferedObjectUsage.STATIC_DRAW);

        //create the VAO
        vao = new VAO(fbo, ibo, indices.length);
        int offset = 0;
        int i = 0;
        for (IVertexAttribute element : vertexElements) {
            int sizeInBytes = element.getElementsPerVertex() * DatatypeUtil.FLOAT_SIZE_BYTES;
            int numberOfVertices = element.getData().length / element.getElementsPerVertex();
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

        for (IVertexAttribute element : vertexElements) {
            totalElementsCount += element.getData().length;
        }

        return totalElementsCount;
    }
}
