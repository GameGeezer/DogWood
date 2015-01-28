package game.weapons;

import framework.graphics.Image;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.components.util.CameraReferenceComponent;
import framework.scene.components.util.TransformComponent;
import framework.util.exceptions.DogWoodException;
import framework.util.fileIO.FileUtil;
import game.Scene;
import game.components.SpriteComponent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Will on 1/13/2015.
 */
public class BasicBullet extends Entity {

    private static Image bulletImage;
    private static String bulletVertexShader, bulletFragmentShader;

    static {

        try {

            bulletImage = Image.loadPNG(new File("res/textures/BulletImage.png"), Image.ImageFormat.RGBA);
            bulletVertexShader = FileUtil.readText("res/shaders/DeferredMeshShader.vert");
            bulletFragmentShader = FileUtil.readText("res/shaders/DeferredMeshShader.frag");

        } catch(DogWoodException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TransformComponent bulletTransform;

    public BasicBullet(TransformComponent transform) {

        ShaderProgram bulletShader = null;

        try {

            Map<Integer, String> attributes = new HashMap<>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_Normal");
            attributes.put(2, "in_TextureCoord");

            bulletShader = new ShaderProgram(bulletVertexShader, bulletFragmentShader, attributes);

        } catch (DogWoodException e) {
            e.printStackTrace();
        }

        bulletTransform = new TransformComponent();
        bulletTransform.setTranslation(transform.getX(), transform.getY(), transform.getZ());
        bulletTransform.setScale(0.1f, 0.1f, 0);

        try {

            SpriteComponent sprite = new SpriteComponent(bulletImage, bulletShader, 1, 1);
            addComponent(sprite);
            addComponent(bulletTransform);
            addComponent(new CameraReferenceComponent(Scene.getCamera()));

        } catch (DogWoodException e) {
            e.printStackTrace();
        }
    }
}
