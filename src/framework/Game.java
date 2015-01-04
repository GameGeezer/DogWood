package framework;

/**
 * @author William Gervasio
 */

public abstract class Game {

    private IScreen screen;

    public Game() {

    }

    /**
     * A Screen must set in the init
     */
    public abstract void init();

    public void update(int delta) {

        screen.update(delta);
    }

    public void setScreen(IScreen screen) {

        if(this.screen != null)
            this.screen.onLeave();

        this.screen = screen;
        this.screen.onResume();
    }
}