package framework.util;

/**
 * @author William Gervasio
 */
public class Grid2D<E> {

    private final E[][] grid;
    private final int length, height;

    public Grid2D(final int length, final int height) {

        this.length = length;
        this.height = height;

        grid = (E[][]) new Object[length][height];
    }

    public E get(final int x, final int y) {

        return grid[x][y];
    }

    public int getLength() {

        return length;
    }

    public int getHeight() {

        return height;
    }
}