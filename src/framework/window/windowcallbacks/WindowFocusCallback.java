package framework.window.windowcallbacks;

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
 * @see WindowFocusListener
 */
public final class WindowFocusCallback extends GLFWWindowFocusCallback {

    public final Collection<WindowFocusListener> listeners = new HashSet<>();

    @Override
    public void invoke(final long window, final int focused) {
        switch (focused) {
            case GL_TRUE:
                listeners.forEach(WindowFocusListener::onWindowGainedFocus);
                break;
            case GL_FALSE:
                listeners.forEach(WindowFocusListener::onWindowLostFocus);
                break;
            default:
                // This should never actually happen
                throw new IllegalStateException("Illegal GLFWWindowFocusCallback 'focused' state");
        }
    }

    public void addListener(final WindowFocusListener listener) {
        listeners.add(listener);
    }

    public void removeListener(final WindowFocusListener listener) {
        listeners.remove(listener);
    }

    public void clearListeners() {
        listeners.clear();
    }
}
