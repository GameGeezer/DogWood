package engine;

import framework.window.Game;
import framework.window.IScreen;
import game.GameScreen;

/**
 * @author William Gervasio
 */
public class Engine extends Game {

    @Override
    public void init() {

        IScreen gameScreen = new GameScreen();

        setScreen(gameScreen);
    }
}
