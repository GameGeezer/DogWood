package game;

import framework.IScreen;
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

    private Scene scene = new Scene();
    private UniformCamera camera = new UniformCamera(800, 600, 0.1f, 100, 60);

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
            spriteSheet = Image.loadPNG(new File("res/textures/walkingSprite1.png"));
        } catch(DogWoodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Entity entity = new Entity();

        try {
            SpriteComponent sprite = new SpriteComponent(spriteSheet, shader, 3, 4);
            entity.addComponent(sprite);

            TransformComponent transform = new TransformComponent();
            transform.setTranslation(0, -0.5f, -3f);
            transform.setOrientationEuler(0, (float) Math.PI, 0);
            transform.setScale(0.25f, 0.25f, 0.25f);
            entity.addComponent(transform);

            entity.addComponent(new UniformCameraReferenceComponent(camera));
            entity.addComponent(new PlayerControllerComponent());
            entity.addComponent(new PlayerUpdateComponent());
        } catch(DogWoodException e) {
            e.printStackTrace();
        }


        scene.addEntity(entity);
    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {

        scene.update(delta);
        scene.render(delta);
    }
}
