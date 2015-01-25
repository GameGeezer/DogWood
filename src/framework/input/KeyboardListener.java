package framework.input;

/**
 * Created by Will on 12/19/2014.
 */
public interface KeyboardListener {

	public default void onKeyUp ( final int keyCode ) {}

	public default void onKeyDown ( final int keyCode ) {}

	public default void onKeyRepeat ( final int keyCode ) {}

}
