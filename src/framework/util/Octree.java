package framework.util;

/**
 * Created by Will on 12/17/2014.
 */
/*
public class Octree {

    enum OctreePosition {
        TOP_FRONT_LEFT(0, 21), TOP_BACK_LEFT(1, 22), TOP_BACK_RIGHT(2, 26), TOP_FRONT_RIGHT(3, 25),
        BOTTOM_FRONT_LEFT(4, 37), BOTTOM_BACK_LEFT(5, 38), BOTTOM_BACK_RIGHT(6, 42), BOTTOM_FRONT_RIGHT(7, 41);

        final int index, id;

        OctreePosition(int index, int id) {
            this.index = index;
            this.id = id;
        }

        public int getIndex() {
            return index;
        }

        public int getId() {
            return id;
        }
    }

    private static final int FRONT = 1;
    private static final int BACK = 2;

    private static final int LEFT = 4;
    private static final int RIGHT = 8;

    private static final int TOP = 16;
    private static final int BOTTOM = 32;

    private static final int TOP_FRONT_LEFT = 21;
    private static final int TOP_BACK_LEFT = 22;

    private static final int TOP_BACK_RIGHT = 26;
    private static final int TOP_FRONT_RIGHT = 25;

    private static final int BOTTOM_FRONT_LEFT = 37;
    private static final int BOTTOM_BACK_LEFT = 38;

    private static final int BOTTOM_BACK_RIGHT = 42;
    private static final int BOTTOM_FRONT_RIGHT = 41;


    private Octree[] subtrees = new Octree[8];
    private int depth, cellsAcross;

    public Octree(int depth) {
        this.depth = depth;
        cellsAcross = (int) Math.pow(2, depth);
    }

    public int getCellsAcross() {
        return cellsAcross;
    }

    public void setCellAt(int x, int y, int z) {

        if(depth == 1) {

        }



        if (grid.val[0] < isolevel) cubeindex |= 1;
        if (grid.val[1] < isolevel) cubeindex |= 2;
        if (grid.val[2] < isolevel) cubeindex |= 4;
        if (grid.val[3] < isolevel) cubeindex |= 8;
        if (grid.val[4] < isolevel) cubeindex |= 16;
        if (grid.val[5] < isolevel) cubeindex |= 32;
        if (grid.val[6] < isolevel) cubeindex |= 64;
        if (grid.val[7] < isolevel) cubeindex |= 128;

        if(x > halfAcross) {
            x -= halfAcross;
        }
        if(y > halfAcross) {
            y -= halfAcross;
        }
        if(z > halfAcross) {
            z -= halfAcross;
        }
    }

    private int getCellIndexFromPosition(int x, int y, int z) {
        float halfAcross = cellsAcross / 2;
        int positionId = 0;

        positionId |= x > halfAcross ? RIGHT : LEFT;
        positionId |= y > halfAcross ? TOP : BOTTOM;
        positionId |= z > halfAcross ? BACK : FRONT;

        switch(positionId) {
            case TOP_FRONT_LEFT:
                return OctreePosition.TOP_FRONT_LEFT.getIndex();
                break;
            case TOP_BACK_LEFT:
                break;
            case TOP_BACK_RIGHT:
                break;
            case TOP_FRONT_RIGHT:
                break;
            case BOTTOM_FRONT_LEFT:
                break;
            case BOTTOM_BACK_LEFT:
                break;
            case BOTTOM_BACK_RIGHT:
                break;
            case BOTTOM_FRONT_RIGHT:
                break;
        }

    }
}
*/