package game;

/**
 * Created by Will on 12/24/2014.
 */
public class SpriteAnimation {

    private int[] cells;
    private int timePerCell;

    public SpriteAnimation(int duration, int... cells) {

        this.cells = cells;
        setDuration(duration);
    }

    public int getFrameIndex(int startTime) {

        final int timePassed = (int)System.currentTimeMillis() - startTime;
        final int index = (timePassed / timePerCell) % cells.length;

        return cells[index];
    }

    public void setDuration(int duration) {

        timePerCell = duration / cells.length;
    }
}
