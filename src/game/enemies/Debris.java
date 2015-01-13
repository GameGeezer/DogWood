package game.enemies;

import framework.graphics.Image;
import framework.graphics.opengl.ShaderProgram;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Will on 1/13/2015.
 */
public class Debris extends Entity{

    private static Image image;
    private static String vertexShader, fragmentShader;
    private TransformComponent transform;

    static {

        try {

            image = Image.loadPNG(new File("res/textures/poulpi.png"));
            vertexShader = FileUtil.readText("res/shaders/SpriteShader.vert");
            fragmentShader = FileUtil.readText("res/shaders/SpriteShader.frag");

        } catch(DogWoodException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Debris(float initialPositionX, float initialPositionY, float initialVelocityX, float initialVelocityY) {

        ShaderProgram bulletShader = null;

        try {

            Map<Integer, String> attributes = new HashMap<>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_TextureCoord");

            bulletShader = new ShaderProgram(vertexShader, fragmentShader, attributes);

        } catch (DogWoodException e) {
            e.printStackTrace();
        }

        transform = new TransformComponent();
        transform.setTranslation(initialPositionX, initialPositionY, -3f);
        transform.setScale(0.1f, 0.1f, 0);

        DynamicComponent bulletDynamicComponent = new DynamicComponent(2f, 2f);
        bulletDynamicComponent.setVelocity(initialVelocityX, initialVelocityY);

        try {

            SpriteComponent sprite = new SpriteComponent(image, bulletShader, 1, 1);
            addComponent(sprite);
            addComponent(transform);
            addComponent(bulletDynamicComponent);
            addComponent(new DebrisUpdateComponent());
            addComponent(new CameraReferenceComponent(Scene.getCamera()));
            addComponent(new AABBComponent(0, 0, 1, 1));

        } catch (DogWoodException e) {
            e.printStackTrace();
        }

    }
}
