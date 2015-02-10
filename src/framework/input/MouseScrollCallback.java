package framework.input;

import org.lwjgl.glfw.GLFWScrollCallback;

import java.util.Collection;
import java.util.HashSet;

/**
 * A GLFWScrollCallback for dispatching MouseScrollListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWScrollCallback
 * @see framework.input.MouseScrollListener
 */
public final class MouseScrollCallback extends GLFWScrollCallback {

	private final Collection < MouseScrollListener > listeners = new HashSet < > ();

	@Override
	public void invoke ( final long window, final double xoffset, final double yoffset ) {

		for ( final MouseScrollListener listener : listeners ) {
			listener.onMouseScroll ( xoffset, yoffset );
		}
	}

    public void addListener(MouseScrollListener listener) {

        listeners.add(listener);
    }

    public void removeListener(MouseScrollListener listener) {

        listeners.remove(listener);
    }

    public void clearListeners() {

        listeners.clear();
    }
}
