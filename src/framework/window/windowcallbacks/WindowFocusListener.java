package framework.window.windowcallbacks;

/**
 * An interface for Objects that want to subscribe to window focus events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowFocusCallback
 */
public interface WindowFocusListener {

	/**
	 * Called when the window loses focus.
	 */
	public default void onWindowLostFocus () {}

	/**
	 * Called when the window gains focus.
	 */
	public default void onWindowGainedFocus () {}

}
