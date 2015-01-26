package framework.input;

import org.lwjgl.glfw.GLFWKeyCallback;

import java.lang.annotation.Inherited;
import java.util.Collection;
import java.util.HashSet;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_REPEAT;

/**
 * A GLFWKeyCallback for dispatching KeyboardListener events.
 *
 * @author Erik Ginter
 * @see org.lwjgl.glfw.GLFWKeyCallback
 * @see framework.input.KeyboardListener
 */
public final class KeyboardCallback extends GLFWKeyCallback {

	public final Collection < KeyboardListener > listeners;

	public KeyboardCallback () {
		listeners = new HashSet < KeyboardListener > ();
	}

	@Override
	public void invoke ( final long window, final int key, final int scancode, final int action, final int mods ) {

		switch ( action ) {

			case GLFW_REPEAT: {
				for ( final KeyboardListener listener : listeners ) {
					listener.onKeyRepeat ( key );
				}
			}
			break;

			case GLFW_PRESS: {
				for ( final KeyboardListener listener : listeners ) {
					listener.onKeyDown ( key );
				}
			}
			break;

			case GLFW_RELEASE: {
				for ( final KeyboardListener listener : listeners ) {
					listener.onKeyUp ( key );
				}
			}
			break;

			default: {
				// This should never actually happen
				throw new IllegalStateException ( "Illegal GLFWKeyCallback 'action' state" );
			}

		}

	}

}
