package game;

import framework.Game;
import framework.IScreen;

/**
 * Created by Will on 4/29/14.
 */
public class DogWoodGame extends Game {

    public void init() {

        IScreen gameScreen = new GameScreen();

        setScreen(gameScreen);
    }
}
