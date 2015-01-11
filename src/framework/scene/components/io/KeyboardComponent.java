package framework.scene.components.io;

import framework.input.IKeyboardListener;
import framework.input.Keyboard;
import framework.scene.Entity.EntityComponent;

/**
 * @author William Gervasio
 */
public abstract class KeyboardComponent extends EntityComponent implements IKeyboardListener {

    @Override
    protected void onAttach() {

        Keyboard.addListener(this);
    }

    @Override
    protected void onDetach() {

        Keyboard.removeListener(this);
    }

}
