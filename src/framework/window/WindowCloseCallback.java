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

	public final Collection < WindowCloseListener > listeners;

	public WindowCloseCallback () {
		listeners = new HashSet < WindowCloseListener > ();
	}

	@Override
	public void invoke ( final long window ) {
		for ( final WindowCloseListener listener : listeners ) {
			listener.onWindowClosing ();
		}
	}

}
