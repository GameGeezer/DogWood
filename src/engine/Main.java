package engine;

import framework.window.Application;
import framework.window.Game;

/**
 * @author William Gervasio
 */
public class Main {

    public static void main(String[] args) {

        final Game game = new Engine ();

        final String TITLE = "DogWood";
        final int WIDTH = 800;
        final int HEIGHT = 600;

        final Runnable application = new Application( game, TITLE, WIDTH, HEIGHT );

        final Thread applicationThread = new Thread ( application, "Application Thread" );
        applicationThread.start ();
    }
}
