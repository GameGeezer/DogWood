package framework.input;

/**
 * Created by Will on 12/19/2014.
 */
public interface IKeyboardListener {

    public abstract void onKeyUp(int keyCode);

    public abstract void onKeyDown(int keyCode);

    public abstract void onKeyRepeat(int keyCode);
}
