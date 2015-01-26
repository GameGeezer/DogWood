package framework.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import java.util.Collection;
import java.util.HashSet;

/**
 * A GLFWCursorPosCallback for dispatching MouseMoveListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWCursorPosCallback
 * @see framework.input.MouseMoveListener
 */
public final class MouseMoveCallback extends GLFWCursorPosCallback {

	public final Collection < MouseMoveListener > listeners;

	public MouseMoveCallback () {
		listeners = new HashSet < MouseMoveListener > ();
	}

	@Override
	public void invoke ( final long window, final double xpos, final double ypos ) {

		for ( final MouseMoveListener listener : listeners ) {
			listener.onMouseMove ( xpos, ypos );
		}

	}
}
