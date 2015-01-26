package framework.window;

import org.lwjgl.glfw.GLFWWindowSizeCallback;

import java.util.Collection;
import java.util.HashSet;

/**
 * A GLFWWindowSizeCallback for dispatching WindowResizeListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowSizeCallback
 * @see framework.window.WindowResizeListener
 */
public final class WindowResizeCallback extends GLFWWindowSizeCallback {

	public final Collection < WindowResizeListener > listeners;

	public WindowResizeCallback () {
		listeners = new HashSet < WindowResizeListener > ();
	}

	@Override
	public void invoke ( final long window, final int width, final int height ) {
		for ( final WindowResizeListener listener : listeners ) {
			listener.onWindowResize ( width, height );
		}
	}

}
