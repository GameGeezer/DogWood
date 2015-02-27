package framework.window.mousecallbacks;

/**
 * An interface for Objects that want to subscribe to mouse scrolling events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWScrollCallback
 */
public interface MouseScrollListener {

    /**
     * Called when the mouse ( input device ) is scrolled.
     *
     * @param dx the horizontal scroll distance
     * @param dy the vertical scroll distance
     */
    public default void onMouseScroll(final double dx, final double dy) {
    }

}
