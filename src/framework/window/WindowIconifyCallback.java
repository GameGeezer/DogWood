package framework.window;

import org.lwjgl.glfw.GLFWWindowIconifyCallback;

import java.util.Collection;
import java.util.HashSet;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;

/**
 * A GLFWWindowIconifyCallback for dispatching WindowIconifyListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWWindowIconifyCallback
 * @see framework.window.WindowIconifyListener
 */
public final class WindowIconifyCallback extends GLFWWindowIconifyCallback {

	private final Collection < WindowIconifyListener > listeners = new HashSet < > ();

	@Override
	public void invoke ( final long window, final int iconified ) {

		switch ( iconified ) {

			case GL_TRUE: {
                listeners.forEach(framework.window.WindowIconifyListener::onWindowIconify);
			}
			break;

			case GL_FALSE: {
                listeners.forEach(framework.window.WindowIconifyListener::onWindowRestore);
			}
			break;

			default: {
				// This should never actually happen
				throw new IllegalStateException ( "Illegal GLWindowIconifyCallback 'iconified' state" );
			}

		}
	}

    public void addListener(WindowIconifyListener listener) {

        listeners.add(listener);
    }

    public void removeListener(WindowIconifyListener listener) {

        listeners.remove(listener);
    }

    public void clearListeners() {

        listeners.clear();
    }
}
