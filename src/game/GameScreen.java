package game;

import framework.IScreen;
import framework.graphics.Camera;
import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.opengl.*;
import framework.scene.Entity;
import framework.scene.Scene;
import framework.util.exceptions.RequiredComponentsException;
import game.components.MeshComponent;
import game.components.SpriteRenderComponent;
import game.components.TransformComponent;
import framework.util.exceptions.DogWoodException;
import framework.util.exceptions.GraphicsException;
import framework.util.fileIO.FileUtil;
import framework.util.fileIO.mesh.WavefrontLoader;
import org.lwjgl.opengl.GL11;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author William Gervasio
 */
public class GameScreen implements IScreen {

    private Scene scene = new Scene();
    TransformComponent transform;
    private UniformCamera camera = new UniformCamera(800, 600, 0.1f, 100, 60);

    public void onPause() {

    }

    public void onResume() {



        ShaderProgram shader = null;
        Mesh teapot = null;
        try {
            Map<Integer, String> attributes = new HashMap<Integer, String>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_Color");
            attributes.put(2, "in_TextureCoord");

            shader = new ShaderProgram(FileUtil.readText("res/testShader.vert"), FileUtil.readText("res/TestShader.frag"), attributes);

            WavefrontLoader loader = new WavefrontLoader();
            teapot = loader.load(new File("res/models/UtahTeapot.obj"));
        } catch(GraphicsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Entity entity = new Entity();
        transform = new TransformComponent(entity);
       // transform.setScale(1, 0.5f, 1);
        transform.setTranslation(0, 0f , 0f);
        transform.addListener(shader);
        camera.addListener(shader);
        // transform.setScale(1, 2f, 1);
        transform.setOrientationEuler(0, (float)Math.PI , 0);

        //transform.translate(10, 10, 10);

        MeshComponent meshComponent = new MeshComponent(entity, teapot, shader);
        entity.addComponent(meshComponent);
        entity.addComponent(transform);
        scene.addEntity(entity);
    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {

        scene.update(delta);
        transform.rotateEuler(0, (float)Math.PI/ 200 , 0);
        camera.setWidth(camera.getWidth() + .1f);
       // transform.translate(0, 0.1f, 0.0f);
       // transform.translate(0, 0.1f, 0.0f);
    }
}
