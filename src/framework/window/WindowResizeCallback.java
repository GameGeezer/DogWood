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

	public final Collection < WindowResizeListener > listeners = new HashSet < > ();

	@Override
	public void invoke ( final long window, final int width, final int height ) {

		for ( final WindowResizeListener listener : listeners ) {
			listener.onWindowResize ( width, height );
		}
	}

    public void addListener(WindowResizeListener listener) {

        listeners.add(listener);
    }

    public void removeListener(WindowResizeListener listener) {

        listeners.remove(listener);
    }

    public void clearListeners() {

        listeners.clear();
    }
}
