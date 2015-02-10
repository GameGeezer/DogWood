package framework;

/**
 * Created by Will on 12/6/2014.
 */
public interface IScreen {

    public abstract void onPause();

    public abstract void onResume();

    public abstract void onLeave();

    public abstract void onResize(int width, int height);

    public abstract void onDestroy();

    public abstract void update(int delta);
}
