package framework.util;

/**
 * @author William Gervasio
 */
public class GridUtil {

    public static<E> void setEdges(Grid3D<E> grid, E value) {

        for(int x = 0; x < grid.getLength(); ++x) {

            for(int y = 0; y < grid.getHeight(); ++y) {

                grid.set(x, y, 0, value);
                grid.set(x, y, grid.getDepth() -1, value);
            }
        }

        for(int z = 1; z < grid.getDepth() - 1; ++z) {

            for(int y = 0; y < grid.getHeight(); ++y) {

                grid.set(0, y, z, value);
                grid.set(grid.getLength() - 1, y, z, value);
            }
        }

        for(int x = 1; x < grid.getLength() - 1; ++x) {

            for(int z = 1; z < grid.getDepth() - 1; ++z) {

                grid.set(x, 0, z , value);
                grid.set(x, grid.getHeight() - 1, z , value);
            }
        }
    }
}
