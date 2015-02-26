package game.weapons;

import framework.graphics.Image;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.components.collision.BoxFixtureComponent;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.scene.components.general.CameraReferenceComponent;
import framework.scene.components.TransformComponent;
import framework.util.exceptions.DogWoodException;
import framework.util.fileIO.FileUtil;
import framework.util.math.Vector2;
import game.Scene;
import framework.scene.components.graphics.SpriteComponent;
import org.jbox2d.dynamics.BodyType;

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

    public BasicBullet(float x, float y, float z, Vector2 direction) {

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
        bulletTransform.setTranslation(x, y, z);
        bulletTransform.setScale(0.1f, 0.1f, 0);

        try {

            SpriteComponent sprite = new SpriteComponent(bulletImage, bulletShader, 1, 1);
            addComponent(sprite);
            addComponent(bulletTransform);
            addComponent(new CameraReferenceComponent(Scene.getCamera()));
            PhysicsBodyComponent body = new PhysicsBodyComponent(BodyType.DYNAMIC, x, y);
           // body.move(x, y);
            body.setLinearVelocity(10f * direction.getX(), 10f * direction.getY());
            addComponent(body);
            addComponent(new BoxFixtureComponent(0.05f, 0.05f, 0, 0, 0));

        } catch (DogWoodException e) {
            e.printStackTrace();
        }
    }
}
