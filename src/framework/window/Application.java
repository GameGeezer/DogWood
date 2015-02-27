package framework.window;

import framework.util.Timer;
import framework.window.keyboardcallbacks.KeyboardCallback;
import framework.window.keyboardcallbacks.KeyboardListener;
import framework.window.windowcallbacks.*;
import groovy.lang.GroovyShell;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.common.Vec2;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * THIS IS A MESS AND IS MOSTLY TUTORIAL CODE FROM LWJGL'S WEBSITE. MAKE ME PRETTY
 *
 * @author William Gervasio
 */
public class Application implements Runnable {

    public static final KeyboardCallback KEYBOARD = new KeyboardCallback();
    public static final WindowCloseCallback WINDOW_CLOSE_CALLBACK = new WindowCloseCallback();
    public static final WindowFocusCallback WINDOW_FOCUS_CALLBACK = new WindowFocusCallback();
    public static final WindowIconifyCallback WINDOW_ICONIFY_CALLBACK = new WindowIconifyCallback();
    public static final WindowMoveCallback WINDOW_MOVE_CALLBACK = new WindowMoveCallback();
    public static final WindowRefreshCallback WINDOW_REFRESH_CALLBACK = new WindowRefreshCallback();
    public static final WindowResizeCallback WINDOW_RESIZE_CALLBACK = new WindowResizeCallback();
    public static final GroovyShell GROOVY_SHELL = new GroovyShell();
    public static final World PHYSICS_WORLD = new World(new Vec2(0f, 0f));

    private final Game game;
    private final String title;
    private final int width;
    private final int height;

    private final Timer timer = new Timer();

    private GLFWErrorCallback errorCallback;

    private long window;

    public Application(final Game game, final String title, final int width, final int height) {
        this.game = game;
        this.title = title;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {

        try {
            init();
            loop();

        } finally {
            destroy();
            glfwTerminate();
            errorCallback.release();
        }
    }

    private void init() {

        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));

        if (glfwInit() != GL11.GL_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable

        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwSetKeyCallback(window, KEYBOARD);
        glfwSetWindowCloseCallback(window, WINDOW_CLOSE_CALLBACK);
        glfwSetWindowFocusCallback(window, WINDOW_FOCUS_CALLBACK);
        glfwSetWindowIconifyCallback(window, WINDOW_ICONIFY_CALLBACK);
        glfwSetWindowPosCallback(window, WINDOW_MOVE_CALLBACK);
        glfwSetWindowRefreshCallback(window, WINDOW_REFRESH_CALLBACK);
        glfwSetWindowSizeCallback(window, WINDOW_RESIZE_CALLBACK);

        KEYBOARD.addListener(new KeyboardListener() {

            @Override
            public void onKeyDown(final int keyCode) {
                if (keyCode == GLFW_KEY_ESCAPE) {
                    glfwSetWindowShouldClose(window, GL_TRUE);
                }
            }
        });

        final ByteBuffer vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Center the window on the screen
        glfwSetWindowPos(
                window,
                (GLFWvidmode.width(vidMode) - width) / 2,
                (GLFWvidmode.height(vidMode) - height) / 2
        );

        glfwMakeContextCurrent(window);

        // Enable VSYNC
        glfwSwapInterval(1);

        glfwShowWindow(window);
    }

    private void loop() {

        GLContext.createFromCurrent();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        game.init();
        timer.start();

        while (glfwWindowShouldClose(window) == GL_FALSE) {

            glfwSwapBuffers(window); // swap the color buffers

            final int delta = (int) timer.getElapsedTimeMS();

            timer.reset();

            game.update(delta);

            glfwPollEvents();
        }

    }

    private void destroy() {

        glfwDestroyWindow(window);
        KEYBOARD.clearListeners();
        KEYBOARD.release();
        WINDOW_CLOSE_CALLBACK.clearListeners();
        WINDOW_CLOSE_CALLBACK.release();
        WINDOW_FOCUS_CALLBACK.clearListeners();
        WINDOW_FOCUS_CALLBACK.release();
        WINDOW_ICONIFY_CALLBACK.clearListeners();
        WINDOW_ICONIFY_CALLBACK.release();
        WINDOW_MOVE_CALLBACK.clearListeners();
        WINDOW_MOVE_CALLBACK.release();
        WINDOW_REFRESH_CALLBACK.clearListeners();
        WINDOW_REFRESH_CALLBACK.release();
        WINDOW_RESIZE_CALLBACK.clearListeners();
        WINDOW_RESIZE_CALLBACK.release();
    }
}
