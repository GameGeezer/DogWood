package framework.scene.components.util;

import framework.input.KeyboardListener;
import framework.scene.Entity;

import static framework.Application.KEYBOARD;

/**
 * @author William Gervasio
 */
public abstract class KeyboardListenerComponent extends Entity.EntityComponent implements KeyboardListener {

    @Override
    protected void onAttach () {

        KEYBOARD.addListener(this);
    }

    @Override
    protected void onDetach () {

        KEYBOARD.removeListener ( this );
    }

    @Override
    protected void onComponentAttachedToParent ( final Entity.EntityComponent component ) {

    }

    @Override
    protected void onComponentDetachedFromParent ( final Entity.EntityComponent component ) {

    }
}
