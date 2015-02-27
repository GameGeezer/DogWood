package framework.window.windowcallbacks;

/**
 * An interface for Objects that want to subscribe to window iconify events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowFocusCallback
 */
public interface WindowIconifyListener {

    /**
     * Called when the window is iconified from the screen ( minimized ).
     */
    public default void onWindowIconify() {
    }

    /**
     * Called when the window is restored to the screen.
     */
    public default void onWindowRestore() {
    }

}
