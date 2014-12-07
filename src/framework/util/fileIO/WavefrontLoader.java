package framework.util.fileIO;

import framework.graphics.Mesh;
import framework.util.FloatArrayList;
import framework.util.fileIO.loaderTypes.IMeshLoader;

import java.io.File;
import java.io.IOException;

/**
 * Created by Will on 12/6/2014.
 */
public class WavefrontLoader implements IMeshLoader {
    //The index of the data type i.e. "v" is vertex and "vn" is vertex normal
    public static final int TYPE_INDEX = 0;

    enum LineTypes {
        V, VN, VT, F
    }

    public WavefrontLoader() {

    }

    public Mesh load(File file) throws IOException {
        String raw = FileUtil.readText(file.getAbsolutePath());
        String[] lines = raw.split("\n");
        FloatArrayList vertices = new FloatArrayList();
        FloatArrayList normals = new FloatArrayList();
        FloatArrayList textureCoordinates = new FloatArrayList();
        for(String line : lines) {
            String[] data = line.split(" ");

            if(data[TYPE_INDEX].equals("v")) {
                System.out.println("Vertex");
            } else if(data[TYPE_INDEX].equals("vn")){
                System.out.println("Normal");
            } else if(data[TYPE_INDEX].equals("vt")){
                System.out.println("Texture");
            } else if(data[TYPE_INDEX].equals("f")){
                System.out.println("Face");
            }
        }
        return null;
    }

    private void parseVertex() {

    }

    private void parseNormal() {

    }

    private void parseFace() {

    }
}
