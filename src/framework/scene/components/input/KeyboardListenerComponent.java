package framework.scene.components.input;

import framework.scene.Entity;
import framework.window.Application;
import framework.window.keyboardcallbacks.KeyboardListener;

/**
 * @author William Gervasio
 */
public abstract class KeyboardListenerComponent extends Entity.EntityComponent implements KeyboardListener {

    @Override
    protected void onAttach() {

        Application.KEYBOARD.addListener(this);
    }

    @Override
    protected void onDetach() {

        Application.KEYBOARD.removeListener(this);
    }

    @Override
    protected void onComponentAttachedToParent(final Entity.EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(final Entity.EntityComponent component) {

    }
}
