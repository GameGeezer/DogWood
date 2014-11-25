package game;

import framework.Application;
import framework.Game;
import framework.Screen;

/**
 * Created by Will on 4/29/14.
 */
public class Main {
    public static void main(String[] args) {
        Application application = new Application(new DogWoodGame(), "DogWood", 800, 600);
    }
}
