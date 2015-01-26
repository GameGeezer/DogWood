package framework.window;

import org.lwjgl.glfw.GLFWWindowPosCallback;

import java.util.Collection;
import java.util.HashSet;

/**
 * A GLFWWindowPosCallback for dispatching WindowMoveListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowPosCallback
 * @see framework.window.WindowMoveListener
 */
public final class WindowMoveCallback extends GLFWWindowPosCallback {

	public final Collection < WindowMoveListener > listeners;

	public WindowMoveCallback () {
		listeners = new HashSet < WindowMoveListener > ();
	}

	@Override
	public void invoke ( final long window, final int xpos, final int ypos ) {
		for ( final WindowMoveListener listener : listeners ) {
			listener.onWindowMove ( xpos, ypos );
		}
	}

}
