package framework.util;

import framework.graphics.Mesh;
import framework.graphics.vertices.DynamicVertexAttribute;
import framework.graphics.vertices.IVertexAttribute;
import framework.graphics.vertices.StaticVertexAttribute;
import framework.util.dataTypes.FloatArrayList;
import framework.util.dataTypes.IntArrayList;

import java.util.*;

/**
 * Created by Will on 12/7/2014.
 */
public class MeshBuilder {

    private Map<String, DynamicVertexAttribute> components = new HashMap<String, DynamicVertexAttribute>();
    private IntArrayList indices = new IntArrayList();

    public MeshBuilder() {

    }

    public void addToComponent(String name, float... data) {

        components.get(name).addData(data);
    }

    public void createComponent(String name, int size) {

        components.put(name, new DynamicVertexAttribute(size));
    }

    public void addIndicie(int indice) {
        indices.add(indice);
    }

    public Mesh build() {
        List<IVertexAttribute> attributes = new ArrayList<>();
        Iterator it = components.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            attributes.add((DynamicVertexAttribute)pairs.getValue());
        }

        return new Mesh(indices.getAsArray(), attributes);
    }
}
