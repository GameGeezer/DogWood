package engine;

import framework.window.Game;
import framework.window.Screen;
import game.GameScreen;

/**
 * @author William Gervasio
 */
public class Engine extends Game {

    @Override
    public void init() {

        Screen gameScreen = new GameScreen();

        setScreen(gameScreen);
    }
}
