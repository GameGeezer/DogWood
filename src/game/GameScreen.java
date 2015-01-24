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
        ShaderProgram shader2 = null;
        Image spriteSheet = null;

        try {
            Map<Integer, String> attributes = new HashMap<Integer, String>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_TextureCoord");

            shader = new ShaderProgram(FileUtil.readText("res/shaders/SpriteShader.vert"), FileUtil.readText("res/shaders/SpriteShader.frag"), attributes);
            shader2 = new ShaderProgram(FileUtil.readText("res/shaders/SpriteShader.vert"), FileUtil.readText("res/shaders/SpriteShader.frag"), attributes);
            spriteSheet = Image.loadPNG(new File("res/textures/ShipImage.png"), Image.ImageFormat.RGBA);

        } catch (DogWoodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Entity collisionTestEntity = new Entity();

        TransformComponent ctTransform = new TransformComponent();
        ctTransform.setTranslation(-1, 0f, -3f);
        ctTransform.setScale(0.3f, 0.3f, 0);

        //create sprite
        Entity spriteEntity = new Entity();

        Player player = new Player();

        Scene.addEntity(player);

        try {

            SpriteComponent ctsprite = new SpriteComponent(spriteSheet, shader2, 1, 1);
            collisionTestEntity.addComponent(ctsprite);
            collisionTestEntity.addComponent(ctTransform);
            collisionTestEntity.addComponent(new CameraReferenceComponent(Scene.getCamera()));
            collisionTestEntity.addComponent(new AABBComponent(0, 0, 1, 1));

            Scene.addEntity(collisionTestEntity);

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
