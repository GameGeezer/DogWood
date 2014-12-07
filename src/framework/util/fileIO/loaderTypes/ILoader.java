package framework.util.fileIO.loaderTypes;

import java.io.File;
import java.io.IOException;

/**
 * Created by Will on 12/6/2014.
 */
public interface ILoader<T> {
    public abstract T load(File file) throws IOException;
}
