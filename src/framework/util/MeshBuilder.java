package framework.util;

import framework.graphics.Mesh;
import framework.graphics.vertices.DynamicVertexAttribute;
import framework.graphics.vertices.VertexAttribute;
import framework.util.dataTypes.IntArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author William Gervasio
 */
public class MeshBuilder {

    private final Map<String, DynamicVertexAttribute> components = new HashMap<>();
    private final IntArrayList indices = new IntArrayList();

    public void addToComponent(final String name, final float... data) {

        components.get(name).addData(data);
    }

    public void createComponent(final String name, final int size) {

        components.put(name, new DynamicVertexAttribute(size));

    }

    public void addIndex(final int index) {

        indices.add(index);
    }

    public Mesh build() {

        final List<VertexAttribute> attributes = components.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());

        return new Mesh(indices.toArray(), attributes);
    }
}
