package framework.util;

/**
 * @author william gervasio
 */

public class QuadTree {
    public static final int TOP_LEFT = 0;
    public static final int TOP_RIGHT = 1;
    public static final int BOTTOM_RIGHT = 2;
    public static final int BOTTOM_LEFT = 3;

    private QuadTree[] branches = new QuadTree[4];

    int level;

    public QuadTree(int level) {
        this.level = level;
    }
}
