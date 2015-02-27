package engine.voxelitemedit;

import framework.scene.Entity;
import framework.scene.components.UpdateComponent;
import framework.util.Grid3D;
import framework.window.Application;
import framework.window.keyboardcallbacks.KeyboardListener;

import static org.lwjgl.glfw.GLFW.*;
/**
 * @author William Gervasio
 */
public class EditVolumeController extends UpdateComponent implements KeyboardListener {

    private Grid3D<Integer> grid;

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
    public void onKeyUp ( final int keyCode ) {

        switch ( keyCode ) {

            case GLFW_KEY_A:

                break;

            case GLFW_KEY_D:

                break;

            case GLFW_KEY_W:

                break;

            case GLFW_KEY_S:

                break;

        }
    }

    @Override
    public void onKeyDown ( final int keyCode ) {

        switch (keyCode) {

            case GLFW_KEY_A:

                break;

            case GLFW_KEY_D:

                break;

            case GLFW_KEY_W:

                break;

            case GLFW_KEY_S:

                break;
        }
    }
}
