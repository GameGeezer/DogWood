package framework.util;

/**
 * Created by Will on 1/2/2015.
 */
public class Grid3D<E> {

    private final E[][][] grid;
    private final int length, height, depth;

    public Grid3D(int length, int height, int depth) {

        this.length = length;
        this.height = height;
        this.depth = depth;

        grid = (E[][][]) new Object[length][height][depth];
    }

    public E get(int x, int y, int z) {

        return grid[x][y][z];
    }

    public int getLength() {

        return length;
    }

    public int getHeight() {

        return height;
    }

    public int getDepth() {

        return depth;
    }
}
