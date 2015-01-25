package framework.input;

import org.lwjgl.glfw.GLFWCursorEnterCallback;

import java.util.Collection;
import java.util.HashSet;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;

/**
 * @author Erik Ginter
 */
public final class MouseEnterLeaveCallback extends GLFWCursorEnterCallback {

	public final Collection < MouseEnterLeaveListener > listeners;

	public MouseEnterLeaveCallback () {
		listeners = new HashSet < MouseEnterLeaveListener > ();
	}

	@Override
	public void invoke ( final long window, final int entered ) {

		switch ( entered ) {

			case GL_TRUE:
				for ( final MouseEnterLeaveListener listener : listeners ) listener.onMouseEnter ();
				break;

			case GL_FALSE:
				for ( final MouseEnterLeaveListener listener : listeners ) listener.onMouseLeave ();
				break;

			default: // This should never happen
				throw new IllegalStateException ( "Illegal GLFWCursorEnterCallback 'entered' state" );

		}

	}
}
