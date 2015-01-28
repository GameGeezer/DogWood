package game.components.player;

import framework.input.KeyboardListener;
import framework.scene.Entity;

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

	private boolean moveLeft = false;
	private boolean moveRight = false;

	private boolean moveUp = false;
	private boolean moveDown = false;

	@Override
	protected void onAttach () {
		KEYBOARD.listeners.add ( this );
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
				moveLeft = false;
				break;

			case GLFW_KEY_D:
				moveRight = false;
				break;

			case GLFW_KEY_W:
				moveUp = false;
				break;

			case GLFW_KEY_S:
				moveDown = false;
				break;

		}

	}

	@Override
	public void onKeyDown ( final int keyCode ) {

		switch ( keyCode ) {

			case GLFW_KEY_A:
				moveLeft = true;
				break;

			case GLFW_KEY_D:
				moveRight = true;
				break;

			case GLFW_KEY_W:
				moveUp = true;
				break;

			case GLFW_KEY_S:
				moveDown = true;
				break;

			case GLFW_KEY_SPACE:
			//	fireBullet ();
				break;

		}

	}

	@Override
	public void onKeyRepeat ( final int keyCode ) {
	}

	public boolean isMoveLeft () {
		return moveLeft;
	}

	public boolean isMoveRight () {
		return moveRight;
	}

	public boolean isMoveUp () {
		return moveUp;
	}

	public boolean isMoveDown () {
		return moveDown;
	}

	/*
	private void fireBullet () {
		final List < TransformComponent > transformComponents = ( List < TransformComponent > ) ( List < ? > ) getParent ().getComponentsOfType ( TransformComponent.class );
		final TransformComponent transformComponent = transformComponents.get ( 0 );

		final List < DynamicComponent > dynamicComponents = ( List < DynamicComponent > ) ( List < ? > ) getParent ().getComponentsOfType ( DynamicComponent.class );
		final DynamicComponent dynamicComponent = dynamicComponents.get ( 0 );

		final BasicBullet bullet = new BasicBullet ( transformComponent, dynamicComponent );
		Scene.addEntity ( bullet );
	}
	*/
}
