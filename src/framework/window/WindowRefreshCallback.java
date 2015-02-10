package framework.window;

import org.lwjgl.glfw.GLFWWindowRefreshCallback;

import java.util.Collection;
import java.util.HashSet;

/**
 * A GLFWWindowRefreshCallback for dispatching WindowRefreshListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowRefreshCallback
 * @see framework.window.WindowRefreshListener
 */
public final class WindowRefreshCallback extends GLFWWindowRefreshCallback {

	public final Collection < WindowRefreshListener > listeners = new HashSet < > ();

	@Override
	public void invoke ( final long window ) {

        listeners.forEach(framework.window.WindowRefreshListener::onWindowRefresh);
	}

    public void addListener(WindowRefreshListener listener) {

        listeners.add(listener);
    }

    public void removeListener(WindowRefreshListener listener) {

        listeners.remove(listener);
    }

    public void clearListeners() {

        listeners.clear();
    }
}
