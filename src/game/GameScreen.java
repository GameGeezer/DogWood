package game;

import framework.IScreen;
import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.opengl.*;
import framework.scene.Entity;
import framework.scene.Scene;
import game.components.*;
import framework.util.exceptions.DogWoodException;
import framework.util.fileIO.FileUtil;
import framework.util.fileIO.mesh.WavefrontLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author William Gervasio
 */
public class GameScreen implements IScreen {

    private Scene scene = new Scene();
    private TransformComponent transform;
    private UniformCamera camera = new UniformCamera(800, 600, 0.1f, 100, 60);
    private Image octapus;

    public void onPause() {

    }

    public void onResume() {

        ShaderProgram shader = null;
        ShaderProgram shader2 = null;
        Mesh teapot = null;
        try {
            Map<Integer, String> attributes = new HashMap<Integer, String>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_Color");
            attributes.put(2, "in_TextureC-------------------------------------------------=d");

           // shader = new ShaderProgram(FileUtil.readText("res/testShader.vert"), FileUtil.readText("res/TestShader.frag"), attributes);
            shader2 = new ShaderProgram(FileUtil.readText("res/shaders/SpriteShader.vert"), FileUtil.readText("res/shaders/SpriteShader.frag"), attributes);
            WavefrontLoader loader = new WavefrontLoader();
            octapus = Image.loadPNG(new File("res/textures/player.png"));
        } catch(DogWoodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Entity entity = new Entity();
        SpriteRenderComponent sprite = new SpriteRenderComponent(octapus,shader2);
        sprite.setFlippedX(true);
        sprite.setFlippedY(false);
        transform = new TransformComponent();
        transform.setTranslation(0, -0.5f , -3f);
        transform.setOrientationEuler(0, (float)Math.PI , 0);

        entity.addComponent(transform);
        entity.addComponent(new UniformCameraReferenceComponent( camera));
        entity.addComponent(new PlayerControllerComponent());

        entity.addComponent(sprite);
        scene.addEntity(entity);
    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {
        scene.update(delta);
        System.out.println(delta);
       // transform.rotateEuler(0, (float)Math.PI/ 200 , 0);
    }
}
