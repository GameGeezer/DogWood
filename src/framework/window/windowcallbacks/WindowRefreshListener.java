package framework.window.windowcallbacks;

/**
 * An interface for Objects that want to subscribe to window refresh events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowRefreshCallback
 */
public interface WindowRefreshListener {

	/**
	 * Called when the window needs to refresh.
	 */
	public default void onWindowRefresh () {}

}
