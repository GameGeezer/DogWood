package game;

import framework.IScreen;
import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.Sprite;
import framework.graphics.opengl.*;
import framework.scene.Entity;
import framework.scene.Scene;
import framework.util.exceptions.RequiredComponentsException;
import game.components.MeshComponent;
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

    public void onPause() {

    }

    public void onResume() {

        Map<Integer, String> attributes = new HashMap<Integer, String>();
        attributes.put(0, "in_Position");
        attributes.put(1, "in_Color");
        attributes.put(2, "in_TextureCoord");
        ShaderProgram shader = null;
        Mesh teapot = null;
        try {
            shader = new ShaderProgram(FileUtil.readText("res/testShader.vert"), FileUtil.readText("res/TestShader.frag"), attributes);
            WavefrontLoader loader = new WavefrontLoader();
            teapot = loader.load(new File("res/models/UtahTeapot.obj"));
        } catch(GraphicsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = null;
        try {
            image = Image.loadPNG(new File("res/textures/poulpi.png"));
        } catch (DogWoodException e) {

        }

        Entity entity = new Entity();
        TransformComponent transform = new TransformComponent(entity);
        transform.setTranslation(0, 0, 0);
        transform.setScale(1, 2f, 1);
        entity.addComponent(transform);
        //transform.translate(10, 10, 10);
        try {
            MeshComponent meshComponent = new MeshComponent(entity, teapot, shader);
            entity.addComponent(meshComponent);
        } catch (RequiredComponentsException e) {
            e.printStackTrace();
        }

        scene.addEntity(entity);
    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        scene.update(delta);
    }
}
