package game.components.player.states;

import framework.scene.Entity;
import framework.scene.StateStack;

/**
 * Created by Will on 2/2/2015.
 */
public abstract class MovementState extends StateStack.State{

    public abstract void move(Entity entity, float x, float y);
}
