package game.components.player;

import framework.input.KeyboardListener;
import framework.scene.Entity;
import framework.scene.components.util.TransformComponent;
import framework.util.math.Vector2;
import game.Scene;
import game.SpriteAnimation;
import game.weapons.BasicBullet;

import java.util.List;

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

    private List<TransformComponent> transformComponents;
	private float horizontalMovement = 0f;
	private float verticalMovement = 0f;

    private SpriteAnimation walkLeftAnimation = new SpriteAnimation(750, 3, 4, 5);
    private SpriteAnimation walkRightAnimation = new SpriteAnimation(750, 6, 7, 8);
    private SpriteAnimation walkUpAnimation = new SpriteAnimation(750, 9, 10, 11);
    private SpriteAnimation walkDownAnimation = new SpriteAnimation(750, 0, 1, 2);

    private Vector2 faceDirection = Vector2.DOWN;

    private SpriteAnimation currentAnimation;
    private int animationStartTime = 0;


    public PlayerControllerComponent() {

        currentAnimation = walkDownAnimation;
    }
	@Override
	protected void onAttach () {

        transformComponents = (List<TransformComponent>) (List<?>) getParent().getComponentsOfType(TransformComponent.class);
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
                currentAnimation = walkLeftAnimation;
                faceDirection = Vector2.LEFT;
                animationStartTime = (int) System.currentTimeMillis();
				break;

			case GLFW_KEY_D:
				horizontalMovement += 1f;
                currentAnimation = walkRightAnimation;
                faceDirection = Vector2.RIGHT;
                animationStartTime = (int) System.currentTimeMillis();
				break;

			case GLFW_KEY_W:
				verticalMovement += 1f;
                currentAnimation = walkUpAnimation;
                faceDirection = Vector2.UP;
                animationStartTime = (int) System.currentTimeMillis();
				break;

			case GLFW_KEY_S:
				verticalMovement -= 1f;
                currentAnimation = walkDownAnimation;
                faceDirection = Vector2.DOWN;
                animationStartTime = (int) System.currentTimeMillis();
				break;

			case GLFW_KEY_SPACE:
				fireBullet ();
				break;

		}
	}

    public int getAnimationFrame() {

        return currentAnimation.getFrameIndex(animationStartTime);
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

    private void fireBullet() {

        TransformComponent tc =transformComponents.get(0);
        Scene.addEntity(new BasicBullet(tc.getX() + (0.3f * faceDirection.getX()), tc.getY() + (0.3f * faceDirection.getY()), tc.getZ(), faceDirection));
    }
}
