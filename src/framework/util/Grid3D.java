package framework.util;

/**
 * @author William Gervasio
 */
public class Grid3D<E> {

    private final E[][][] grid;
    private final int length, height, depth;

    public Grid3D(final int length, final int height, final int depth) {

        this.length = length;
        this.height = height;
        this.depth = depth;

        grid = (E[][][]) new Object[length][height][depth];
    }

    public E get(final int x, final int y, final int z) {

        return grid[x][y][z];
    }

    public void set(final int x, final int y, final int z, E value) {

        grid[x][y][z] = value;
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
