package game;

import framework.IScreen;
import framework.scene.components.physics.AABBComponent;
import framework.scene.components.util.CameraReferenceComponent;
import framework.scene.components.util.TransformComponent;
import framework.graphics.Image;
import framework.graphics.opengl.*;
import framework.scene.Entity;
import game.components.*;
import framework.util.exceptions.DogWoodException;
import framework.util.fileIO.FileUtil;
import game.components.player.PlayerControllerComponent;
import game.components.player.PlayerUpdateComponent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author William Gervasio
 */
public class GameScreen implements IScreen {

    public void onPause() {

    }

    public void onResume() {

        ShaderProgram shader = null;
        Image spriteSheet = null;

        try {
            Map<Integer, String> attributes = new HashMap<Integer, String>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_TextureCoord");

            shader = new ShaderProgram(FileUtil.readText("res/shaders/SpriteShader.vert"), FileUtil.readText("res/shaders/SpriteShader.frag"), attributes);
            spriteSheet = Image.loadPNG(new File("res/textures/ShipImage.png"));

        } catch (DogWoodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //create sprite
        Entity spriteEntity = new Entity();

        TransformComponent spriteTransform = new TransformComponent();
        spriteTransform.setTranslation(0, -1f, -3f);
        spriteTransform.setOrientationEuler(0, (float) Math.PI, 0);
        spriteTransform.setScale(0.3f, 0.3f, 0);

        try {

            SpriteComponent sprite = new SpriteComponent(spriteSheet, shader, 1, 1);
            spriteEntity.addComponent(sprite);
            spriteEntity.addComponent(spriteTransform);
            spriteEntity.addComponent(new CameraReferenceComponent(Scene.getCamera()));
            spriteEntity.addComponent(new PlayerControllerComponent());
            spriteEntity.addComponent(new PlayerUpdateComponent());
            spriteEntity.addComponent(new AABBComponent(0, 0, 1, 1));
            spriteEntity.addComponent(new DynamicComponent(0.8f, .8f));
            spriteEntity.addComponent(new ArcAtDepthComponent());

            Scene.addEntity(spriteEntity);

        } catch (DogWoodException e) {
            e.printStackTrace();
        }
    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {

        Scene.update(delta);
        Scene.render(delta);
    }
}
