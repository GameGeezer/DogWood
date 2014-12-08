package framework.util.fileIO.mesh.wavefront;

import framework.graphics.Mesh;
import framework.util.fileIO.FileUtil;
import framework.util.fileIO.loaderTypes.IMeshLoader;
import framework.util.math.Vector2;
import framework.util.math.Vector3;

import java.io.File;
import java.io.IOException;

/**
 * Created by Will on 12/6/2014.
 */
public class WavefrontLoader implements IMeshLoader {
    //The index of the data type i.e. "v" is vertex and "vn" is vertex normal
    public static final int TYPE_INDEX = 0;

    public WavefrontLoader() {

    }

    public Mesh load(File file) throws IOException {
        String raw = FileUtil.readText(file.getAbsolutePath());
        String[] lines = raw.split("\n");


        for(String line : lines) {
            String[] data = line.split(" ");
            switch(data[TYPE_INDEX]) {
                case "v":
                    if(data.length != 4)
                        throw new IOException("Line has an invalid number objects: " + line);
                    //vertices.add(new Vector3(Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3])));
                    break;
                case "vn":
                    if(data.length != 4)
                        throw new IOException("Line has an invalid number objects: " + line);
                    //normals.add(new Vector3(Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3])));
                    break;
                case "vt":
                    if(data.length != 3)
                        throw new IOException("Line has an invalid number objects: " + line);
                    //textureCoordinates.add(new Vector2(Float.parseFloat(data[1]), Float.parseFloat(data[2])));
                    break;
                case "f":
                    if(data.length != 4)
                        throw new IOException("Line has an invalid number objects: " + line);
                    for(int i = 1; i + 2 < data.length; i+=3) {
                      //  parseVertex(data[i]);
                    }
                    break;
            }
        }
        return null;
    }
}