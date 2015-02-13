package game;

import framework.window.Application;
import framework.window.Game;

/**
 * Created by Will on 4/29/14.
 */
public class Main {

	public static void main ( final String[] args ) {

		final Game game = new DogWoodGame ();

		final String TITLE = "DogWood";
		final int WIDTH = 800;
		final int HEIGHT = 600;

		final Runnable application = new Application( game, TITLE, WIDTH, HEIGHT );

		final Thread applicationThread = new Thread ( application, "Application Thread" );
		applicationThread.start ();

	}
}
