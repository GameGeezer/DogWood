package framework.util;

import framework.graphics.vertices.DynamicVertexAttribute;
import framework.util.dataTypes.FloatArrayList;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Will on 12/7/2014.
 */
public class MeshBuilder {
    private Map<String, DynamicVertexAttribute> components = new HashMap<String, DynamicVertexAttribute>();

    public MeshBuilder() {

    }

    public void addToComponent(String name, float... data) {
        components.get(name).addData(data);
    }

    public void createComponent(String name, int size) {
        components.put(name, new DynamicVertexAttribute(size));
    }
}
