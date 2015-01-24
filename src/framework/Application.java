package framework;

import framework.input.Keyboard;
import framework.util.Timer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

/**
 * THIS IS A MESS AND IS MOSTLY TUTORIAL CODE FROM LWJWL'S WEBSITE. MAKE ME PRETTY
 *
 * @author William Gervasio
 */

public class Application implements Runnable {

    private final Game game;
    private final String title;
    private final int width;
    private final int height;

    private final Timer timer;

    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    private long window;

    public Application ( Game game, String title, int width, int height ) {
        this.game = game;
        this.title = title;
        this.width = width;
        this.height = height;
        timer = new Timer ();
    }

    @Override
    public void run () {
        try {
            init ();
            loop ();

            glfwDestroyWindow ( window );
            keyCallback.release ();
        } finally {
            glfwTerminate ();
            errorCallback.release ();
        }
    }

    private void init () {

        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));

        if (glfwInit() != GL11.GL_TRUE)
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable

        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop

                Keyboard.handleKeyChange(window, key, scancode, action, mods);
            }
        });

        final ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Center the window on the screen
        glfwSetWindowPos(
                window,
                (GLFWvidmode.width(vidmode) - width) / 2,
                (GLFWvidmode.height(vidmode) - height) / 2
        );

        glfwMakeContextCurrent(window);

        // Enable VSYNC
        glfwSwapInterval(1);

        glfwShowWindow(window);
    }

    private void loop () {

        GLContext.createFromCurrent ();

        glClearColor ( 0.0f, 0.0f, 0.0f, 0.0f );

        game.init ();
        timer.start ();

        while ( glfwWindowShouldClose ( window ) == GL_FALSE ) {
            //glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers ( window ); // swap the color buffers

            final int delta = ( int ) timer.getElapsedTimeMS ();
            timer.reset ();

            game.update ( delta );

            glfwPollEvents ();
        }

    }
}
