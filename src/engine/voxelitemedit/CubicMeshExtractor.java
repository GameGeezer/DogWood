package engine.voxelitemedit;

import framework.graphics.Mesh;
import framework.util.Grid3D;
import framework.util.MeshBuilder;

import java.util.ArrayList;

/**
 * @author  William Gervasio
 */
public class CubicMeshExtractor {

    private static final String POSITION_COMPONENT = "POSITION";
    private static final String TEXTURE_COMPONENT = "TEXCOORD";
    private static final String NORMAL_COMPONENT = "NORMAL";

    private static final int POSITION_SIZE = 3;
    private static final int TEXTURE_SIZE = 2;
    private static final int NORMAL_SIZE = 3;

    public static Mesh extractDirty(Grid3D<Integer> grid) {

        final MeshBuilder builder = new MeshBuilder();
        ArrayList<Integer> emptySpace = new ArrayList<>();
        emptySpace.add(0);
        int vertexIndex = 0;

        builder.createComponent(POSITION_COMPONENT, POSITION_SIZE);
        builder.createComponent(TEXTURE_COMPONENT, TEXTURE_SIZE);
        builder.createComponent(NORMAL_COMPONENT, NORMAL_SIZE);

        for(int x = 0; x < grid.getLength() - 1; ++ x) {

            for(int y = 0; y < grid.getHeight() - 1; ++ y) {

                for(int z = 0; z < grid.getDepth() - 1; ++z) {

                    if(checkForTransitionX(grid, x, y, z, emptySpace)) {

                        addQuadX(builder, x,y, z, vertexIndex);
                        vertexIndex += 4;
                    }

                    if(checkForTransitionY(grid, x, y, z, emptySpace)) {

                        addQuadY(builder, x, y, z, vertexIndex);
                        vertexIndex += 4;
                    }

                    if(checkForTransitionZ(grid, x, y, z, emptySpace)) {

                        addQuadZ(builder, x, y, z, vertexIndex);
                        vertexIndex += 4;
                    }
                }
            }
        }

        return  builder.build();
    }


    private static boolean checkForTransitionX(Grid3D<Integer> grid, int x, int y, int z, ArrayList<Integer> emptySpace) {

        return emptySpace.contains(grid.get(x, y, z)) != emptySpace.contains(grid.get(x + 1, y, z));
    }

    private static boolean checkForTransitionY(Grid3D<Integer> grid, int x, int y, int z, ArrayList<Integer> emptySpace) {

        return emptySpace.contains(grid.get(x, y, z)) != emptySpace.contains(grid.get(x, y + 1, z));
    }

    private static boolean checkForTransitionZ(Grid3D<Integer> grid, int x, int y, int z, ArrayList<Integer> emptySpace) {

        return emptySpace.contains(grid.get(x, y, z)) != emptySpace.contains(grid.get(x, y, z + 1));
    }

    private static void addQuadX(MeshBuilder builder, int x, int y, int z, int vertexIndex) {

        builder.addToComponent(POSITION_COMPONENT, x + 1, y, z);
        builder.addToComponent(POSITION_COMPONENT, x + 1, y + 1, z);
        builder.addToComponent(POSITION_COMPONENT, x + 1, y, z + 1);
        builder.addToComponent(POSITION_COMPONENT, x + 1, y + 1, z + 1);

        builder.addToComponent(TEXTURE_COMPONENT, 0, 0);
        builder.addToComponent(TEXTURE_COMPONENT, 0, 1);
        builder.addToComponent(TEXTURE_COMPONENT, 1, 0);
        builder.addToComponent(TEXTURE_COMPONENT, 1, 1);

        builder.addToComponent(NORMAL_COMPONENT, 1, 0, 0);
        builder.addToComponent(NORMAL_COMPONENT, 1, 0, 0);
        builder.addToComponent(NORMAL_COMPONENT, 1, 0, 0);
        builder.addToComponent(NORMAL_COMPONENT, 1, 0, 0);

        builder.addIndex(vertexIndex);
        builder.addIndex(vertexIndex + 1);
        builder.addIndex(vertexIndex + 2);
        builder.addIndex(vertexIndex + 1);
        builder.addIndex(vertexIndex + 2);
        builder.addIndex(vertexIndex + 3);
    }

    private static void addQuadY(MeshBuilder builder, int x, int y, int z, int vertexIndex) {

        builder.addToComponent(POSITION_COMPONENT, x, y + 1, z);
        builder.addToComponent(POSITION_COMPONENT, x + 1, y + 1, z);
        builder.addToComponent(POSITION_COMPONENT, x, y + 1, z + 1);
        builder.addToComponent(POSITION_COMPONENT, x + 1, y + 1, z + 1);

        builder.addToComponent(TEXTURE_COMPONENT, 0, 0);
        builder.addToComponent(TEXTURE_COMPONENT, 0, 1);
        builder.addToComponent(TEXTURE_COMPONENT, 1, 0);
        builder.addToComponent(TEXTURE_COMPONENT, 1, 1);

        builder.addToComponent(NORMAL_COMPONENT, 0, 1, 0);
        builder.addToComponent(NORMAL_COMPONENT, 0, 1, 0);
        builder.addToComponent(NORMAL_COMPONENT, 0, 1, 0);
        builder.addToComponent(NORMAL_COMPONENT, 0, 1, 0);

        builder.addIndex(vertexIndex);
        builder.addIndex(vertexIndex + 1);
        builder.addIndex(vertexIndex + 2);
        builder.addIndex(vertexIndex + 1);
        builder.addIndex(vertexIndex + 2);
        builder.addIndex(vertexIndex + 3);
    }

    private static void addQuadZ(MeshBuilder builder, int x, int y, int z, int vertexIndex) {

        builder.addToComponent(POSITION_COMPONENT, x, y, z + 1);
        builder.addToComponent(POSITION_COMPONENT, x + 1, y, z + 1);
        builder.addToComponent(POSITION_COMPONENT, x, y + 1, z + 1);
        builder.addToComponent(POSITION_COMPONENT, x + 1, y + 1, z + 1);

        builder.addToComponent(TEXTURE_COMPONENT, 0, 0);
        builder.addToComponent(TEXTURE_COMPONENT, 0, 1);
        builder.addToComponent(TEXTURE_COMPONENT, 1, 0);
        builder.addToComponent(TEXTURE_COMPONENT, 1, 1);

        builder.addToComponent(NORMAL_COMPONENT, 0, 0, 1);
        builder.addToComponent(NORMAL_COMPONENT, 0, 0, 1);
        builder.addToComponent(NORMAL_COMPONENT, 0, 0, 1);
        builder.addToComponent(NORMAL_COMPONENT, 0, 0, 1);

        builder.addIndex(vertexIndex);
        builder.addIndex(vertexIndex + 1);
        builder.addIndex(vertexIndex + 2);
        builder.addIndex(vertexIndex + 1);
        builder.addIndex(vertexIndex + 2);
        builder.addIndex(vertexIndex + 3);
    }
}
