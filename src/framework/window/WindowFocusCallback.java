package framework.window;

import org.lwjgl.glfw.GLFWWindowFocusCallback;

import java.util.Collection;
import java.util.HashSet;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;

/**
 * A GLFWWindowFocusCallback for dispatching WindowFocusListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowFocusCallback
 * @see framework.window.WindowFocusListener
 */
public final class WindowFocusCallback extends GLFWWindowFocusCallback {

	public final Collection < WindowFocusListener > listeners = new HashSet < > ();

	@Override
	public void invoke ( final long window, final int focused ) {

		switch ( focused ) {

			case GL_TRUE:
				for ( final WindowFocusListener listener : listeners ) {
					listener.onWindowGainedFocus ();
				}
			break;

			case GL_FALSE:
				for ( final WindowFocusListener listener : listeners ) {
					listener.onWindowLostFocus ();
				}
			break;

			default:
				// This should never actually happen
				throw new IllegalStateException ( "Illegal GLFWWindowFocusCallback 'focused' state" );

		}
	}

    public void addListener(WindowFocusListener listener) {

        listeners.add(listener);
    }

    public void removeListener(WindowFocusListener listener) {

        listeners.remove(listener);
    }

    public void clearListeners() {

        listeners.clear();
    }
}
