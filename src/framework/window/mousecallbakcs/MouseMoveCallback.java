package framework.window.mousecallbakcs;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import java.util.Collection;
import java.util.HashSet;

/**
 * A GLFWCursorPosCallback for dispatching MouseMoveListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWCursorPosCallback
 * @see MouseMoveListener
 */
public final class MouseMoveCallback extends GLFWCursorPosCallback {

	private final Collection < MouseMoveListener > listeners = new HashSet <> ();

    @Override
	public void invoke ( final long window, final double xpos, final double ypos ) {
		for ( final MouseMoveListener listener : listeners ) {
			listener.onMouseMove ( xpos, ypos );
		}
	}

    public void addListener ( final MouseMoveListener listener ) {
        listeners.add ( listener );
    }

    public void removeListener ( final MouseMoveListener listener ) {
        listeners.remove ( listener );
    }

    public void clearListeners () {
        listeners.clear ();
    }
}
