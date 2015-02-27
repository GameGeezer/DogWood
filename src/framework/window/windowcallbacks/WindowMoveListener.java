package framework.window.windowcallbacks;

/**
 * An interface for Objects that want to subscribe to window movement events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowPosCallback
 */
public interface WindowMoveListener {

    /**
     * Called when the window is moved to a new location.
     *
     * @param x the new screen-relative x coordinate of the window
     * @param y the new screen-relative y coordinate of the window
     */
    public default void onWindowMove(final int x, final int y) {
    }

}
