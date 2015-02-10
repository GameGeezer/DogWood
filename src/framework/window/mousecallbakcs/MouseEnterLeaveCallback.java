package framework.window.mousecallbakcs;

import org.lwjgl.glfw.GLFWCursorEnterCallback;

import java.util.Collection;
import java.util.HashSet;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;

/**
 * A GLFWCursorEnterCallback for dispatching MouseEnterLeaveListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWCursorEnterCallback
 * @see MouseEnterLeaveListener
 */
public final class MouseEnterLeaveCallback extends GLFWCursorEnterCallback {

	private final Collection < MouseEnterLeaveListener > listeners = new HashSet < > ();

	@Override
	public void invoke ( final long window, final int entered ) {

		switch ( entered ) {

			case GL_TRUE:
                listeners.forEach(MouseEnterLeaveListener::onMouseEnter);
			break;

			case GL_FALSE:
                listeners.forEach(MouseEnterLeaveListener::onMouseLeave);
			break;

			default:
				// This should never happen
				throw new IllegalStateException ( "Illegal GLFWCursorEnterCallback 'entered' state" );

		}
	}

    public void addListener(MouseEnterLeaveListener listener) {

        listeners.add(listener);
    }

    public void removeListener(MouseEnterLeaveListener listener) {

        listeners.remove(listener);
    }

    public void clearListeners() {

        listeners.clear();
    }
}
