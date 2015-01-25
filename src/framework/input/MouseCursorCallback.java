package framework.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import java.util.Collection;
import java.util.HashSet;

public final class MouseCursorCallback extends GLFWCursorPosCallback {

	public final Collection < MouseCursorListener > listeners;

	public MouseCursorCallback () {
		listeners = new HashSet < MouseCursorListener > ();
	}

	@Override
	public void invoke ( final long window, final double xpos, final double ypos ) {

		for ( final MouseCursorListener listener : listeners ) {
			listener.onMouseMove ( xpos, ypos );
		}

	}
}
