package game.components.player;

import framework.graphics.Image;
import framework.graphics.opengl.ShaderProgram;
import framework.input.IKeyboardListener;
import framework.input.Keyboard;
import framework.scene.Entity;
import framework.scene.components.physics.AABBComponent;
import framework.scene.components.util.CameraReferenceComponent;
import framework.scene.components.util.TransformComponent;
import framework.util.exceptions.DogWoodException;
import framework.util.fileIO.FileUtil;
import game.Scene;
import game.components.DynamicComponent;
import game.components.SpriteComponent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Will on 12/18/2014.
 */

public class PlayerControllerComponent extends Entity.EntityComponent implements IKeyboardListener {

    private boolean moveLeft = false;
    private boolean moveRight = false;

    private Image bulletImage;
    private String bulletVertexShader, bulletFragmentShader;
    private List<Entity> bullets = new ArrayList<>();

    public PlayerControllerComponent() {

        try {

            bulletImage = Image.loadPNG(new File("res/textures/BulletImage.png"));
            bulletVertexShader = FileUtil.readText("res/shaders/SpriteShader.vert");
            bulletFragmentShader = FileUtil.readText("res/shaders/SpriteShader.frag");

        } catch(DogWoodException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onAttach() {

        Keyboard.addListener(this);
    }

    @Override
    protected void onDetach() {

        Keyboard.removeListener(this);
    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

    }


    @Override
    public void onKeyUp(int keyCode) {

        switch(keyCode) {

            case Keyboard.KEY_A:
                moveLeft = false;

                break;

            case Keyboard.KEY_D:
                moveRight = false;

                break;
        }
    }

    @Override
    public void onKeyDown(int keyCode) {

        switch(keyCode) {

            case Keyboard.KEY_A:
                moveLeft = true;
                break;

            case Keyboard.KEY_D:
                moveRight = true;
                
                break;

            case Keyboard.KEY_SPACE:
                fireBullet();
                break;
        }
    }

    @Override
    public void onKeyRepeat(int keyCode) {

    }

    public boolean isMoveLeft() {

        return moveLeft;
    }

    public boolean isMoveRight() {

        return moveRight;
    }

    private void fireBullet() {

        ShaderProgram bulletShader = null;

        try {
            Map<Integer, String> attributes = new HashMap<>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_TextureCoord");

            bulletShader = new ShaderProgram(bulletVertexShader, bulletFragmentShader, attributes);

        } catch (DogWoodException e) {
            e.printStackTrace();
        }

        List<TransformComponent> transformComponents = (List<TransformComponent>) (List<?>) getParent().getComponentsOfType(TransformComponent.class);
        TransformComponent transformComponent = transformComponents.get(0);

        Entity bulletEntity = new Entity();

        TransformComponent bulletTransform = new TransformComponent();
        bulletTransform.setTranslation(transformComponent.getX(), transformComponent.getY(), -3f);
        bulletTransform.setScale(0.1f, 0.1f, 0);

        List<DynamicComponent> dynamicComponents = (List<DynamicComponent>) (List<?>) getParent().getComponentsOfType(DynamicComponent.class);
        DynamicComponent dynamicComponent = dynamicComponents.get(0);

        DynamicComponent bulletDynamicComponent = new DynamicComponent(2f, 2f);
        bulletDynamicComponent.setVelocity(dynamicComponent.getVelocityX(), dynamicComponent.getVelocityY() + 1);

        try {

            SpriteComponent sprite = new SpriteComponent(bulletImage, bulletShader, 1, 1);
            bulletEntity.addComponent(sprite);
            bulletEntity.addComponent(bulletTransform);
            bulletEntity.addComponent(bulletDynamicComponent);
            bulletEntity.addComponent(new CameraReferenceComponent(Scene.getCamera()));
            bulletEntity.addComponent(new AABBComponent(0, 0, 1, 1));

        } catch (DogWoodException e) {
            e.printStackTrace();
        }

        Scene.addEntity(bulletEntity);
        bullets.add(bulletEntity);
    }
}
