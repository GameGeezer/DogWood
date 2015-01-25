package framework.util;

import framework.graphics.Mesh;
import framework.graphics.vertices.DynamicVertexAttribute;
import framework.graphics.vertices.VertexAttribute;
import framework.util.dataTypes.IntArrayList;

import java.util.*;

/**
 * Created by Will on 12/7/2014.
 */
public class MeshBuilder {

    private final Map<String, DynamicVertexAttribute> components = new HashMap<String, DynamicVertexAttribute>();
    private final IntArrayList indices = new IntArrayList();

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

        List<VertexAttribute> attributes = new ArrayList<>();

        final Iterator it = components.entrySet().iterator();

        while (it.hasNext()) {

            final Map.Entry pairs = (Map.Entry) it.next();

            attributes.add((DynamicVertexAttribute) pairs.getValue());
        }

        return new Mesh(indices.getAsArray(), attributes);
    }
}
