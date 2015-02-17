package framework.window.windowcallbacks;

import org.lwjgl.glfw.GLFWWindowSizeCallback;

import java.util.Collection;
import java.util.HashSet;

/**
 * A GLFWWindowSizeCallback for dispatching WindowResizeListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowSizeCallback
 * @see WindowResizeListener
 */
public final class WindowResizeCallback extends GLFWWindowSizeCallback {

	public final Collection < WindowResizeListener > listeners = new HashSet <> ();

	@Override
	public void invoke ( final long window, final int width, final int height ) {
		for ( final WindowResizeListener listener : listeners ) {
			listener.onWindowResize ( width, height );
		}
	}

    public void addListener ( final WindowResizeListener listener ) {
        listeners.add ( listener );
    }

    public void removeListener ( final WindowResizeListener listener ) {
        listeners.remove ( listener );
    }

    public void clearListeners () {
        listeners.clear ();
    }
}
