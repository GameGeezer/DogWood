package framework.window;

/**
 * An interface for Objects that want to subscribe to window close events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowCloseCallback
 */
public interface WindowCloseListener {

	/**
	 * Called when the user requests the window to close.
	 */
	public void onWindowClosing ();

}
