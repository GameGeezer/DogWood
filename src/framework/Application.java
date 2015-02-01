package framework;

import framework.input.KeyboardCallback;
import framework.input.KeyboardListener;
import framework.util.Timer;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * THIS IS A MESS AND IS MOSTLY TUTORIAL CODE FROM LWJWL'S WEBSITE. MAKE ME PRETTY
 *
 * @author William Gervasio
 */
public class Application implements Runnable {

	public static final KeyboardCallback KEYBOARD = new KeyboardCallback ();

	private final Game game;
	private final String title;
	private final int width;
	private final int height;

	private final Timer timer;

	private GLFWErrorCallback errorCallback;

	private long window;

	public Application ( final Game game, final String title, final int width, final int height ) {
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
			KEYBOARD.listeners.clear ();
			KEYBOARD.release ();
		} finally {
			glfwTerminate ();
			errorCallback.release ();
		}
	}

	private void init () {

		glfwSetErrorCallback ( errorCallback = errorCallbackPrint ( System.err ) );

		if ( glfwInit () != GL11.GL_TRUE ) {
			throw new IllegalStateException ( "Unable to initialize GLFW" );
		}

		glfwDefaultWindowHints (); // optional, the current window hints are already the default
		glfwWindowHint ( GLFW_VISIBLE, GL_FALSE ); // the window will stay hidden after creation
		glfwWindowHint ( GLFW_RESIZABLE, GL_TRUE ); // the window will be resizable

		window = glfwCreateWindow ( width, height, title, NULL, NULL );
		if ( window == NULL ) {
			throw new RuntimeException ( "Failed to create the GLFW window" );
		}

		glfwSetKeyCallback ( window, KEYBOARD );

		KEYBOARD.listeners.add ( new KeyboardListener () {
			@Override
			public void onKeyDown ( final int keyCode ) {
				if ( keyCode == GLFW_KEY_ESCAPE ) {
					glfwSetWindowShouldClose ( window, GL_TRUE );
				}
			}
		} );

		final ByteBuffer vidMode = glfwGetVideoMode ( glfwGetPrimaryMonitor () );

		// Center the window on the screen
		glfwSetWindowPos (
				window,
				( GLFWvidmode.width ( vidMode ) - width ) / 2,
				( GLFWvidmode.height ( vidMode ) - height ) / 2
		);

		glfwMakeContextCurrent ( window );

		// Enable VSYNC
		glfwSwapInterval ( 1 );

		glfwShowWindow ( window );
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
