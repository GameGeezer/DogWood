package framework.input;


import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard {

    private static List<IKeyboardListener> listeners = new ArrayList<>();

    public static final int KEY_A = GLFW_KEY_A;
    public static final int KEY_D = GLFW_KEY_D;

    public static void handleKeyChange(long window, int key, int scancode, int action, int mods) {

        switch (action) {

            case GLFW_PRESS:
                listeners.forEach((listener) -> listener.onKeyDown(key));
                break;

            case GLFW_RELEASE:
                listeners.forEach((listener) -> listener.onKeyUp(key));
                break;

            case GLFW_REPEAT:
                listeners.forEach((listener) -> listener.onKeyRepeat(key));
                break;
        }
    }

    public static void addListener(IKeyboardListener listener) {

        listeners.add(listener);
    }

    public static void removeListener(IKeyboardListener listener) {

        listeners.remove(listener);
    }
}
