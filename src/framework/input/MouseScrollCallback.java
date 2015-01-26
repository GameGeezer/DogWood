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

	public final Collection < MouseScrollListener > listeners;

	public MouseScrollCallback () {
		listeners = new HashSet < MouseScrollListener> ();
	}

	@Override
	public void invoke ( final long window, final double xoffset, final double yoffset ) {

		for ( final MouseScrollListener listener : listeners ) {
			listener.onMouseScroll ( xoffset, yoffset );
		}

	}

}
