package framework.util.fileIO;

import framework.graphics.Mesh;
import framework.util.MeshBuilder;
import framework.util.dataTypes.DatatypeUtil;
import framework.util.fileIO.FileUtil;
import framework.util.math.Vector2;
import framework.util.math.Vector3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William Gervasio
 */
public class WavefrontLoader {

    enum VertexType {
        POSITION, POSITION_TEXCOORD, POSITION_NORMAL, POSITION_TEXCOORD_NORMAL
    }
    //The index of the data type i.e. "v" is vertex and "vn" is vertex normal
    private final int TYPE_INDEX = 0;

    private final String POSITION_COMPONENT = "POSITION";
    private final String TEXCOORD_COMPONENT = "TEXCOORD";
    private final String NORMAL_COMPONENT = "NORMAL";

    private final int POSITION_SIZE = 3;
    private final int TEXCOORD_SIZE = 2;
    private final int NORMAL_SIZE = 3;

    private VertexType expectedVertexType;

    private List<Vector3> positions = new ArrayList<Vector3>();
    private List<Vector2> textureCoordinates = new ArrayList<Vector2>();
    private List<Vector3> normals = new ArrayList<Vector3>();

    private Map<String, Integer> knownVertices = new HashMap<String, Integer>();
    private int vertexCount = 0;

    public WavefrontLoader() {

    }

    public Mesh load(File file) throws IOException {
        String raw = FileUtil.readText(file.getAbsolutePath());
        String[] lines = raw.split("\n");
        MeshBuilder builder = new MeshBuilder();
        setExpectedVertexFormat(lines, builder);

        for(String line : lines) {
            String[] data = line.split(" ");
            switch(data[TYPE_INDEX]) {
                case "v":
                    if(data.length < 4)
                        throw new IOException("Line has an invalid number objects: " + line);
                    positions.add(new Vector3(DatatypeUtil.parseFloat(data[1]), DatatypeUtil.parseFloat(data[2]), DatatypeUtil.parseFloat(data[3])));
                    break;
                case "vn":
                    if(data.length < 4)
                        throw new IOException("Line has an invalid number objects: " + line);
                    normals.add(new Vector3(DatatypeUtil.parseFloat(data[1]), DatatypeUtil.parseFloat(data[2]), DatatypeUtil.parseFloat(data[3])));
                    break;
                case "vt":
                    if(data.length < 3)
                        throw new IOException("Line has an invalid number objects: " + line);
                    textureCoordinates.add(new Vector2(DatatypeUtil.parseFloat(data[1]), DatatypeUtil.parseFloat(data[2])));
                    break;
                case "f":
                    if(data.length < 4 || data.length > 5)
                        throw new IOException("Line has an invalid number objects: " + line);
                    for (int i = 1; i < data.length; ++i) {
                        if (!knownVertices.containsKey(data[i])) {
                            buildVertex(data[i], builder);
                        }
                    }
                    if(data.length == 4) {
                        builder.addIndicie(knownVertices.get(data[1]));
                        builder.addIndicie(knownVertices.get(data[2]));
                        builder.addIndicie(knownVertices.get(data[3]));
                    } else if(data.length == 5) {
                        builder.addIndicie(knownVertices.get(data[1]));
                        builder.addIndicie(knownVertices.get(data[2]));
                        builder.addIndicie(knownVertices.get(data[3]));

                        builder.addIndicie(knownVertices.get(data[2]));
                        builder.addIndicie(knownVertices.get(data[3]));
                        builder.addIndicie(knownVertices.get(data[4]));
                    }
            }
        }

        return builder.build();
    }

    private void setExpectedVertexFormat(String[] lines, MeshBuilder builder) throws  IOException{
        String sampleFace = null;
        for(String line : lines) {
            String[] data = line.split(" ");
            if(data[TYPE_INDEX].equals("f"))
                sampleFace = data[1];
        }

        if(sampleFace == null)
            throw new IOException("No Faces found, cannot parse");

        String[] components = sampleFace.split("//");
        builder.createComponent(POSITION_COMPONENT, POSITION_SIZE);
        if(components.length == 2) {
            expectedVertexType = VertexType.POSITION_NORMAL;
            builder.createComponent(NORMAL_COMPONENT, NORMAL_SIZE);
        } else if((components = sampleFace.split("/")).length == 1) {
            expectedVertexType = VertexType.POSITION;
        } else if(components.length == 2) {
            expectedVertexType = VertexType.POSITION_TEXCOORD;
            builder.createComponent(TEXCOORD_COMPONENT, TEXCOORD_SIZE);
        } else if(components.length == 3) {
            expectedVertexType = VertexType.POSITION_TEXCOORD_NORMAL;
            builder.createComponent(TEXCOORD_COMPONENT, TEXCOORD_SIZE);
            builder.createComponent(NORMAL_COMPONENT, NORMAL_SIZE);
        } else {
            throw new IOException("Face format is not recognised: " + sampleFace);
        }
    }

    private void buildVertex(String data, MeshBuilder builder) throws IOException {
        String[] components = data.split("//");
        if(components.length == 2) { // position normal
            if(!expectedVertexType.equals(VertexType.POSITION_NORMAL))
                throw new IOException("Inconsistant vertex: " + data);
            int index = Integer.parseInt(components[0]) - 1;
            Vector3 position = positions.get(index);
            builder.addToComponent(POSITION_COMPONENT, position.getX(), position.getY(), position.getZ());

            index = Integer.parseInt(components[1]) - 1;
            Vector3 normal = normals.get(index);
            builder.addToComponent(NORMAL_COMPONENT, normal.getX(), normal.getY(), normal.getZ());

        } else if((components = data.split("/")).length == 1) { // position
            if(!expectedVertexType.equals(VertexType.POSITION))
                throw new IOException("Inconsistant vertex: " + data);
            int index = Integer.parseInt(components[0]) - 1;
            Vector3 position = positions.get(index);
            builder.addToComponent(POSITION_COMPONENT, position.getX(), position.getY(), position.getZ());

        } else if(components.length == 2) { // position texcoord
            if(!expectedVertexType.equals(VertexType.POSITION_TEXCOORD))
                throw new IOException("Inconsistant vertex: " + data);

            int index = Integer.parseInt(components[0]) - 1;
            Vector3 vec3 = positions.get(index);
            builder.addToComponent(POSITION_COMPONENT, vec3.getX(), vec3.getY(), vec3.getZ());

            index = Integer.parseInt(components[1]) - 1;
            Vector2 vec2 = textureCoordinates.get(index);
            builder.addToComponent(TEXCOORD_COMPONENT,vec2.getX(), vec2.getY());

        } else if(components.length == 3) { // position texcoord normal
            if(!expectedVertexType.equals(VertexType.POSITION_TEXCOORD_NORMAL))
                throw new IOException("Inconsistant vertex: " + data);

            int index = Integer.parseInt(components[0]) - 1;
            Vector3 position = positions.get(index);
            builder.addToComponent(POSITION_COMPONENT, position.getX(), position.getY(), position.getZ());

            index = Integer.parseInt(components[1]) - 1;
            Vector2 texCoord = textureCoordinates.get(index);
            builder.addToComponent(TEXCOORD_COMPONENT,  texCoord.getX(), texCoord.getY());

            index = Integer.parseInt(components[2]) - 1;
            Vector3 normal = normals.get(index);
            builder.addToComponent(NORMAL_COMPONENT,normal.getX(), normal.getY(), normal.getZ());
        } else {
            throw new IOException("Vertex could not be parsed: " + data);
        }

        knownVertices.put(data, vertexCount);
        ++vertexCount;
    }
}