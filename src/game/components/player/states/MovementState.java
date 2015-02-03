package game.components.player.states;

import framework.scene.components.collision.PhysicsBodyComponent;

/**
 * Created by Will on 2/2/2015.
 */
public abstract class MovementState extends StateStack.State{

    public abstract void move(PhysicsBodyComponent body, float x, float y);
}
