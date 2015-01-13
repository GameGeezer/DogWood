package framework.input;


import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard {

    private static List<IKeyboardListener> listeners = new ArrayList<>();

    public static final int KEY_A = GLFW_KEY_A;
    public static final int KEY_B = GLFW_KEY_B;
    public static final int KEY_C = GLFW_KEY_C;
    public static final int KEY_D = GLFW_KEY_D;
    public static final int KEY_E = GLFW_KEY_E;
    public static final int KEY_F = GLFW_KEY_F;
    public static final int KEY_G = GLFW_KEY_G;
    public static final int KEY_H = GLFW_KEY_H;
    public static final int KEY_I = GLFW_KEY_I;
    public static final int KEY_J = GLFW_KEY_J;
    public static final int KEY_K = GLFW_KEY_K;
    public static final int KEY_L = GLFW_KEY_L;
    public static final int KEY_M = GLFW_KEY_M;
    public static final int KEY_N = GLFW_KEY_N;
    public static final int KEY_O = GLFW_KEY_O;
    public static final int KEY_P = GLFW_KEY_P;
    public static final int KEY_Q = GLFW_KEY_Q;
    public static final int KEY_R = GLFW_KEY_R;
    public static final int KEY_S = GLFW_KEY_S;
    public static final int KEY_T = GLFW_KEY_T;
    public static final int KEY_U = GLFW_KEY_U;
    public static final int KEY_V = GLFW_KEY_V;
    public static final int KEY_W = GLFW_KEY_W;
    public static final int KEY_X = GLFW_KEY_X;
    public static final int KEY_Y = GLFW_KEY_Y;
    public static final int KEY_Z = GLFW_KEY_Z;
    public static final int KEY_SPACE = GLFW_KEY_SPACE;

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
