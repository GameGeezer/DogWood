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

	public final Collection < WindowRefreshListener > listeners;

	public WindowRefreshCallback () {
		listeners = new HashSet < WindowRefreshListener > ();
	}

	@Override
	public void invoke ( final long window ) {
		for ( final WindowRefreshListener listener : listeners ) {
			listener.onWindowRefresh ();
		}
	}

}
