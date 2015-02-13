package framework.window.mousecallbakcs;

/**
 * An interface for Objects that want to subscribe to mouse cursor enter/leave events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWCursorEnterCallback
 */
public interface MouseEnterLeaveListener {

	/**
	 * Called when the mouse cursor enters the window.
	 */
	public default void onMouseEnter () {}

	/**
	 * Called when the mouse cursor leaves the window.
	 */
	public default void onMouseLeave () {}

}
