package framework.util.fileIO.mesh.wavefront;

import framework.util.dataTypes.IntArrayList;
import framework.util.math.Vector2;
import framework.util.math.Vector3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 12/7/2014.
 */
public class WavefrontMeshBuilder {

    private List<Vector3> positions = new ArrayList<Vector3>();
    private List<Vector2> textureCoordinates = new ArrayList<Vector2>();
    private List<Vector3> normals = new ArrayList<Vector3>();

    IntArrayList indices = new IntArrayList();

    public WavefrontMeshBuilder() {

    }

    public void addPosition(Vector3 position) {
        positions.add(position);
    }

    public void addTextureCoordinate(Vector2 textureCoordinate) {
        textureCoordinates.add(textureCoordinate);
    }

    public void addNormal(Vector3 normal) {
        normals.add(normal);
    }

    public void addFace(String vert1, String vert2, String vert3) {

    }

    private int createVertex(String data) {
        String[] components = data.split("/");
        if(components.length == 1) { //vertex

        } else if(components.length == 2) { //vertex and texture coordinates

        } else if(components.length == 3) { //vertex, texture coordinates, and normals

        }
        return 0;
    }
}
