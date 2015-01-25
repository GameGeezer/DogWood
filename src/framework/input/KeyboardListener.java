package framework.input;

/**
 * @author Erik Ginter
 */
public interface KeyboardListener {

	public default void onKeyUp ( final int keyCode ) {}

	public default void onKeyDown ( final int keyCode ) {}

	public default void onKeyRepeat ( final int keyCode ) {}

}
