package game;

import framework.Application;
import framework.Game;
import framework.Screen;

/**
 * Created by Will on 4/29/14.
 */
public class Main {
    public static void main(String[] args) {
        Game game = new DogWoodGame();
        Screen gameScreen = new GameScreen();
        game.setScreen(gameScreen);
        Application application = new Application(game, "DogWood", 800, 600);
    }
}
