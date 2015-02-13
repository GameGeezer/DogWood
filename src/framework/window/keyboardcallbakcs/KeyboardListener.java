package framework.window.keyboardcallbakcs;

/**
 * An interface for Objects that want to subscribe to keyboard key events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWKeyCallback
 */
public interface KeyboardListener {

	/**
	 * Called when a key on the keyboard is released.
	 *
	 * @param keyCode which key was released
	 */
	public default void onKeyUp ( final int keyCode ) {}

	/**
	 * Called when a key on the keyboard is pressed.
	 *
	 * @param keyCode which key was pressed
	 */
	public default void onKeyDown ( final int keyCode ) {}

	/**
	 * Called when a key on the keyboard is pressed and held.
	 *
	 * @param keyCode which key was held
	 */
	public default void onKeyRepeat ( final int keyCode ) {}

}
