package framework.util.fileIO.loaderTypes;

import framework.graphics.Mesh;

import java.io.File;
import java.io.IOException;

/**
 * Created by Will on 12/6/2014.
 */
public interface IMeshLoader extends ILoader<Mesh> {

    public abstract Mesh load(File file) throws IOException;
}
