package framework.graphics.vertices;

import framework.util.dataTypes.FloatArrayList;

/**
 * Created by Will on 12/7/2014.
 */
public class DynamicVertexAttribute implements IVertexAttribute{

    private final FloatArrayList vertexData = new FloatArrayList();
    private final int elementsPerVertex;

    public DynamicVertexAttribute(int elementsPerVertex) {
        this.elementsPerVertex = elementsPerVertex;
    }

    public float[] getData() {
        return vertexData.getAsArray();
    }

    public int getElementsPerVertex() {
        return elementsPerVertex;
    }

    public void addData(float... data) {
        for(int i = 0; i < data.length; ++i) {
            vertexData.add(data[i]);
        }
    }
}
