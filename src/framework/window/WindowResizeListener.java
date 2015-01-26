package framework.window;

/**
 * An interface for Objects that want to subscribe to window resize events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowSizeCallback
 */
public interface WindowResizeListener {

	/**
	 * Called when the window is resized.
	 *
	 * @param width  the new width of the window
	 * @param height the new height of the window
	 */
	public default void onWindowResize ( final int width, final int height ) {
	}

}
