package framework.util;

import framework.graphics.Mesh;
import framework.graphics.vertices.DynamicVertexAttribute;
import framework.graphics.vertices.VertexAttribute;
import framework.util.dataTypes.IntArrayList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Will on 12/7/2014.
 */
public class MeshBuilder {

	private final Map < String, DynamicVertexAttribute > components = new HashMap < > ();
	private final IntArrayList indices = new IntArrayList ();

	public void addToComponent ( final String name, final float... data ) {

		components.get ( name ).addData ( data );
	}

	public void createComponent ( final String name, final int size ) {

		components.put ( name, new DynamicVertexAttribute ( size ) );

	}

	public void addIndex ( final int index ) {

		indices.add ( index );
	}

	public Mesh build () {

		final List < VertexAttribute > attributes = components.entrySet().stream().map(pairs -> pairs.getValue()).collect(Collectors.toList());

        return new Mesh ( indices.toArray (), attributes );
	}
}
