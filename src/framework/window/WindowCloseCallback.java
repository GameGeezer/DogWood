package framework.window;

import org.lwjgl.glfw.GLFWWindowCloseCallback;

import java.util.Collection;
import java.util.HashSet;

/**
 * A GLFWWindowCloseCallback for dispatching WindowCloseListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowCloseCallback
 * @see framework.window.WindowCloseListener
 */
public final class WindowCloseCallback extends GLFWWindowCloseCallback {

	private final Collection < WindowCloseListener > listeners = new HashSet < > ();

	@Override
	public void invoke ( final long window ) {

		for ( final WindowCloseListener listener : listeners ) {
			listener.onWindowClosing ();
		}
	}

    public void addListener(WindowCloseListener listener) {

        listeners.add(listener);
    }

    public void removeListener(WindowCloseListener listener) {

        listeners.remove(listener);
    }

    public void clearListeners() {

        listeners.clear();
    }
}
