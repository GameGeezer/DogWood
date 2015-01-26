package game;

import framework.graphics.Image;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.components.collision.AABBComponent;
import framework.scene.components.util.CameraReferenceComponent;
import framework.scene.components.util.TransformComponent;
import framework.util.exceptions.DogWoodException;
import framework.util.fileIO.FileUtil;
import framework.scene.components.util.DynamicComponent;
import game.components.SpriteComponent;
import game.components.player.PlayerControllerComponent;
import game.components.player.PlayerUpdateComponent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Will on 1/14/2015.
 */
public class Player extends Entity {

    private static Image image;
    private static String vertexShader, fragmentShader;

    static {

        try {

            image = Image.loadPNG(new File("res/textures/poulpi.png"), Image.ImageFormat.RGBA);
            vertexShader = FileUtil.readText("res/shaders/DeferredMeshShader.vert");
            fragmentShader = FileUtil.readText("res/shaders/DeferredMeshShader.frag");

        } catch(DogWoodException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Player() {

        TransformComponent transform = new TransformComponent();
        transform.setTranslation(-1, -1f, -3f);
        transform.setScale(0.3f, 0.3f, 0);

        try {

            Map<Integer, String> attributes = new HashMap<>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_Normal");
            attributes.put(2, "in_TextureCoord");

            SpriteComponent sprite = new SpriteComponent(image, new ShaderProgram(vertexShader, fragmentShader, attributes), 1, 1);
            addComponent(sprite);
            addComponent(transform);
            addComponent(new CameraReferenceComponent(Scene.getCamera()));
            addComponent(new PlayerControllerComponent());
            addComponent(new PlayerUpdateComponent());
            addComponent(new AABBComponent(0, 0, 1, 1));
            addComponent(new DynamicComponent(0.8f, .8f));

        } catch (DogWoodException e) {

            e.printStackTrace();
        }
    }
}
