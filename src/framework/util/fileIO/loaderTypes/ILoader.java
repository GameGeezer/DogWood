package framework.util.fileIO.loaderTypes;

import java.io.File;

/**
 * Created by Will on 12/6/2014.
 */
public interface ILoader<T> {
    public abstract T load(File file);
}
