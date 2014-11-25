package framework;

import framework.util.Timer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

/**
 * @author William Gervasio
 */

public class Application {

    private Game game;
    private Timer timer = new Timer();

    public Application(Game game, String title, int width, int height) {
        this.game = game;
        initLWJGL(title, width, height);
        start();
    }

    public final void initLWJGL(String title, int width, int height) {

        try {
            PixelFormat pixelFormat = new PixelFormat();
            ContextAttribs contextAtrributes = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);

            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title);
            Display.create(pixelFormat, contextAtrributes);

            GL11.glViewport(0, 0, width, height);
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        GL11.glClearColor(1f, 1f, 1f, 0f);

        GL11.glViewport(0, 0, width, height);
    }

    public final void start() {
        game.init();
        timer.start();
        while (!Display.isCloseRequested()) {
            final int delta = (int) timer.getElapsedTimeMS();
            timer.reset();

            game.update(delta);

            Display.sync(60);
            Display.update();
        }
    }
}