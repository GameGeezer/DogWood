package framework.window.windowcallbacks;

import org.lwjgl.glfw.GLFWWindowCloseCallback;

import java.util.Collection;
import java.util.HashSet;

/**
 * A GLFWWindowCloseCallback for dispatching WindowCloseListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowCloseCallback
 * @see WindowCloseListener
 */
public final class WindowCloseCallback extends GLFWWindowCloseCallback {

	private final Collection < WindowCloseListener > listeners = new HashSet <> ();

	@Override
	public void invoke ( final long window ) {
        listeners.forEach ( WindowCloseListener :: onWindowClosing );
	}

    public void addListener ( final WindowCloseListener listener ) {
        listeners.add ( listener );
    }

    public void removeListener ( final WindowCloseListener listener ) {
        listeners.remove ( listener );
    }

    public void clearListeners () {
        listeners.clear ();
    }
}
