package framework;

/**
 * @author William Gervasio
 */

public interface Screen {

    public abstract void onPause();

    public abstract void onResume();

    public abstract void onLeave();

    public abstract void onResize(int width, int height);

    public abstract void update(int delta);
}