package framework.window;

/**
 * @author William Gervasio
 */

public abstract class Game {

    private Screen screen;

    public Game() {

    }

    /**
     * A Screen must set in the init
     */
    public abstract void init();

    public void update(int delta) {

        screen.update(delta);
    }

    public void setScreen(Screen screen) {

        if (this.screen != null)
            this.screen.onLeave();

        this.screen = screen;
        this.screen.onResume();
    }
}