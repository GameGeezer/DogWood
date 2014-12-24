package framework.scene.components;

import framework.input.IKeyboardListener;
import framework.input.Keyboard;
import framework.scene.Entity.EntityComponent;

/**
 * Created by Will on 12/19/2014.
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
