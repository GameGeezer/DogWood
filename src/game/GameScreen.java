package game;

import framework.IScreen;
import framework.graphics.DeferredRenderer;
import framework.graphics.Mesh;
import framework.scene.components.collision.AABBComponent;
import framework.scene.components.graphics.MeshComponent;
import framework.scene.components.util.CameraReferenceComponent;
import framework.scene.components.util.TransformComponent;
import framework.graphics.Image;
import framework.graphics.opengl.*;
import framework.scene.Entity;
import framework.util.fileIO.WavefrontLoader;
import game.components.*;
import framework.util.exceptions.DogWoodException;
import framework.util.fileIO.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author William Gervasio
 */
public class GameScreen implements IScreen {

    private DeferredRenderer renderer;
    TransformComponent ctTransform;

    public void onPause() {

    }

    public void onResume() {

        ShaderProgram treeShader = null;
        ShaderProgram shader2 = null;
        Image spriteSheet = null;
        Mesh treeMesh = null;

        try {
            Map<Integer, String> attributes = new HashMap<Integer, String>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_TextureCoord");

            treeShader = new ShaderProgram(FileUtil.readText("res/shaders/DeferredMeshShader.vert"), FileUtil.readText("res/shaders/DeferredMeshShader.frag"), attributes);
            shader2 = new ShaderProgram(FileUtil.readText("res/shaders/SpriteShader.vert"), FileUtil.readText("res/shaders/SpriteShader.frag"), attributes);
            spriteSheet = Image.loadPNG(new File("res/textures/ShipImage.png"), Image.ImageFormat.RGBA);
            WavefrontLoader wvLoader = new WavefrontLoader();
            treeMesh = wvLoader.load(new File("res/models/UtahTeapot.obj"));

            renderer = new DeferredRenderer(800, 600);
        } catch (DogWoodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Entity collisionTestEntity = new Entity();

        ctTransform = new TransformComponent();
        ctTransform.setTranslation(-1, 0f, -3f);
        ctTransform.setScale(0.4f, 0.4f, 0.4f);

        //create sprite
        Entity spriteEntity = new Entity();

        Player player = new Player();

        Scene.addEntity(player);

        try {

            MeshComponent ctsprite = new MeshComponent(treeMesh, treeShader);
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

        ctTransform.rotateEuler(0f, (float)Math.PI / 1000f, 0f);
        Scene.update(delta);
        renderer.beginDrawing();
        Scene.render(delta);
        renderer.endDrawing();
    }
}
