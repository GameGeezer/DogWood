package framework.input;

/**
 * An interface for Objects that want to subscribe to mouse movement events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWCursorPosCallback
 */
public interface MouseMoveListener {

	/**
	 * Called when the mouse cursor is moved.
	 *
	 * @param x the new screen-relative x coordinate of the mouse cursor
	 * @param y the new screen-relative y coordinate of the mouse cursor
	 */
	public default void onMouseMove ( final double x, final double y ) {}

}
