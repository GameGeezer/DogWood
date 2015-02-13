package framework.util;

/**
 * Created by Will on 1/26/2015.
 */
public class Grid2D<E> {

    private final E[][] grid;
    private final int length, height;

    public Grid2D(int length, int height) {

        this.length = length;
        this.height = height;

        grid = (E[][]) new Object[length][height];
    }

    public E get(int x, int y) {

        return grid[x][y];
    }

    public int getLength() {

        return length;
    }

    public int getHeight() {

        return height;
    }
}