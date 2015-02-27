package engine.voxelitemedit;

import framework.scene.Entity;
import framework.scene.components.UpdateComponent;
import framework.util.Grid3D;
import framework.util.RangeUtil;
import framework.util.math.Vector3i;
import framework.window.Application;
import framework.window.keyboardcallbacks.KeyboardListener;

import static org.lwjgl.glfw.GLFW.*;

/**
 * @author William Gervasio
 */
public class EditVolumeController extends UpdateComponent implements KeyboardListener {

    private Grid3D<Integer> grid;
    private final Vector3i gridPosition = new Vector3i();

    public EditVolumeController(int length, int height, int depth) {

        grid = new Grid3D<>(length, height, depth);
    }

    @Override
    protected void onAttach() {

        Application.KEYBOARD.addListener(this);
    }

    @Override
    protected void onDetach() {

        Application.KEYBOARD.removeListener(this);
    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

    }

    @Override
    public void update(int delta) {

    }

    @Override
    public void onKeyDown(final int keyCode) {

        switch (keyCode) {

            case GLFW_KEY_A:

                gridPosition.x = RangeUtil.forceIntoRange(gridPosition.x - 1, 0, grid.getLength());
                break;

            case GLFW_KEY_D:

                gridPosition.x = RangeUtil.forceIntoRange(gridPosition.x + 1, 0, grid.getLength());
                break;

            case GLFW_KEY_W:

                gridPosition.z = RangeUtil.forceIntoRange(gridPosition.z + 1, 0, grid.getDepth());
                break;

            case GLFW_KEY_S:

                gridPosition.z = RangeUtil.forceIntoRange(gridPosition.z - 1, 0, grid.getDepth());
                break;

            case GLFW_KEY_Q:

                gridPosition.y = RangeUtil.forceIntoRange(gridPosition.y + 1, 0, grid.getHeight());
                break;

            case GLFW_KEY_E:

                gridPosition.y = RangeUtil.forceIntoRange(gridPosition.y - 1, 0, grid.getHeight());
                break;
        }
    }
}
