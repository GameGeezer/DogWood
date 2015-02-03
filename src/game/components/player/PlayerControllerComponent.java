package game.components.player;

import framework.input.KeyboardListener;
import framework.scene.Entity;
import game.components.player.states.DecelerateMovementState;
import game.components.player.states.MovementState;
import game.components.player.states.StateStack;

import static framework.Application.KEYBOARD;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

/**
 * Created by Will on 12/18/2014.
 */
public class PlayerControllerComponent extends Entity.EntityComponent implements KeyboardListener {

	private float horizontalMovement = 0f;
	private float verticalMovement = 0f;

	@Override
	protected void onAttach () {

		KEYBOARD.listeners.add(this);
	}

	@Override
	protected void onDetach () {

		KEYBOARD.listeners.remove ( this );
	}

	@Override
	protected void onComponentAttachedToParent ( final Entity.EntityComponent component ) {
	}

	@Override
	protected void onComponentDetachedFromParent ( final Entity.EntityComponent component ) {
	}

	@Override
	public void onKeyUp ( final int keyCode ) {

		switch ( keyCode ) {

			case GLFW_KEY_A:
				horizontalMovement += 1f;
				break;

			case GLFW_KEY_D:
				horizontalMovement -= 1f;
				break;

			case GLFW_KEY_W:
				verticalMovement -= 1f;
				break;

			case GLFW_KEY_S:
				verticalMovement += 1f;
				break;

		}

	}

	@Override
	public void onKeyDown ( final int keyCode ) {

		switch ( keyCode ) {

			case GLFW_KEY_A:
				horizontalMovement -= 1f;
				break;

			case GLFW_KEY_D:
				horizontalMovement += 1f;
				break;

			case GLFW_KEY_W:
				verticalMovement += 1f;
				break;

			case GLFW_KEY_S:
				verticalMovement -= 1f;
				break;

			case GLFW_KEY_SPACE:
			//	fireBullet ();
				break;

		}

	}

	@Override
	public void onKeyRepeat ( final int keyCode ) {
	}

	public float getHorizontalMovement() {
		return horizontalMovement;
	}

	public float getVerticalMovement() {
		return verticalMovement;
	}
}
