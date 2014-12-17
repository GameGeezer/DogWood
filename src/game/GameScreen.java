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
import game.components.UniformCameraReferenceComponent;
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
    Image octapus;

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
            attributes.put(2, "in_TextureCoord");

           // shader = new ShaderProgram(FileUtil.readText("res/testShader.vert"), FileUtil.readText("res/TestShader.frag"), attributes);
            shader2 = new ShaderProgram(FileUtil.readText("res/shaders/BasicShader.vert"), FileUtil.readText("res/shaders/BasicShader.frag"), attributes);
            WavefrontLoader loader = new WavefrontLoader();
            teapot = loader.load(new File("res/models/lowPolyTree.obj"));
            octapus = Image.loadPNG(new File("res/textures/poulpi.png"));
        } catch(DogWoodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        Entity entity = new Entity();
        SpriteRenderComponent sprite = new SpriteRenderComponent(octapus,shader2);
        transform = new TransformComponent();
        transform.setScale(.1f, .1f, .1f);
        transform.setTranslation(0, -0.5f , -3f);
        UniformCameraReferenceComponent cameraReference = new UniformCameraReferenceComponent( camera);
        //camera.addListener(shader2);
        // transform.setScale(1, 2f, 1);
        transform.setOrientationEuler(0, (float)Math.PI , 0);

        //transform.translate(10, 10, 10);

        MeshComponent meshComponent = new MeshComponent(teapot, shader2);
        entity.addComponent(meshComponent);
        entity.addComponent(transform);
        entity.addComponent(cameraReference);
      //  entity.addComponent(sprite);
        scene.addEntity(entity);
    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {
        scene.update(delta);
        transform.rotateEuler(0, (float)Math.PI/ 200 , 0);
    }
}
