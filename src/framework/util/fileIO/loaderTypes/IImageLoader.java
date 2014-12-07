package framework.util.fileIO.loaderTypes;

import framework.graphics.Image;

import java.io.File;
import java.io.IOException;

/**
 * Created by Will on 12/6/2014.
 */
public interface IImageLoader extends ILoader<Image> {
    public abstract Image load(File file) throws IOException;
}
