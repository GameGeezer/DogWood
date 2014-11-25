package framework.graphics;

import framework.graphics.opengl.*;
import framework.util.DatatypeUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

/**
 * @author william gervasio
 */

public class Mesh {

    protected VAO vao;

    private List<VertexAttribute> vertexElements;
    private int[] indices;

    public Mesh(int[] indices, List<VertexAttribute> vertexElements) {
        this.indices = indices;
        this.vertexElements = vertexElements;

        //create Vertex Buffer
        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(countTotalElements());
        for (VertexAttribute element : vertexElements) {
            verticesBuffer.put(element.getData());
        }
        verticesBuffer.flip();
        VBO vbo = new VBO(verticesBuffer, BufferedObjectUsage.STATIC_DRAW);

        //create indexBuffer
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices);
        indicesBuffer.flip();
        IBO ibo = new IBO(indicesBuffer, BufferedObjectUsage.STATIC_DRAW);

        //create the VAO
        vao = new VAO(vbo, ibo, indices.length);
        int offset = 0;
        int i = 0;
        for (VertexAttribute element : vertexElements) {
            int sizeInBytes = element.getElementsPerVertex() * DatatypeUtil.FLOAT_SIZE_BYTES;
            int numberOfVertices = element.getData().length / element.getElementsPerVertex();
            vao.addVertexAttribute(i, new Descriptor(element.getElementsPerVertex(), GL11.GL_FLOAT, false, sizeInBytes, offset));
            offset += sizeInBytes * numberOfVertices;
            ++i;
        }
        vao.init();
    }

    public void draw() {
        vao.draw();
    }

    private int countTotalElements() {
        int totalElementsCount = 0;
        for (VertexAttribute element : vertexElements) {
            totalElementsCount += element.getData().length;
        }
        return totalElementsCount;
    }
}
