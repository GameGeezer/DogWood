package game;

import framework.Application;
import framework.IScreen;
import framework.graphics.DeferredRenderer;
import framework.graphics.Mesh;
import framework.scene.components.collision.BoxFixtureComponent;
import framework.scene.components.collision.PhysicsBodyComponent;
import framework.scene.components.graphics.MeshComponent;
import framework.scene.components.graphics.TexturedMeshComponent;
import framework.scene.components.util.CameraReferenceComponent;
import framework.scene.components.util.TransformComponent;
import framework.graphics.Image;
import framework.graphics.opengl.*;
import framework.scene.Entity;
import framework.util.Timer;
import framework.util.fileIO.OBJLoader;
import framework.util.exceptions.DogWoodException;
import framework.util.fileIO.FileUtil;
import framework.util.fileIO.WavefrontLoader;
import framework.util.math.Transform;
import groovy.lang.Script;
import org.jbox2d.dynamics.BodyType;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author William Gervasio
 */
public class GameScreen implements IScreen {

    private DeferredRenderer renderer;
    TransformComponent ctTransform;
    Entity player;

    public void onPause() {

    }

    public void onResume() {

        ShaderProgram treeShader = null;
        Image spriteSheet = null;
        Mesh treeMesh = null;

        try {
            Map<Integer, String> attributes = new HashMap<Integer, String>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_TextureCoord");

            treeShader = new ShaderProgram(FileUtil.readText("res/shaders/DeferredMeshShader.vert"), FileUtil.readText("res/shaders/DeferredMeshShader.frag"), attributes);
            spriteSheet = Image.loadPNG(new File("res/textures/walls128.png"), Image.ImageFormat.RGBA);
            Transform playerTransform = new Transform();
            playerTransform.setTranslation(0, 0, -1.2f);
            playerTransform.rotateEuler((float)Math.PI / 2.5f, 0f, 0f);
            playerTransform.setScale(0.25f, 0.25f, 0);
            Timer timer = new Timer();
            Script s = Application.GROOVY_SHELL.parse( new File( "res/scripts/BuildScripts.groovy" ) );
            timer.start();

            player = (Entity) s.invokeMethod("buildPlayer", playerTransform ) ;
            timer.pause();
            System.out.println(timer.getElapsedTimeMS());
            Scene.addEntity(player);
            treeMesh = WavefrontLoader.LOADER.load(new File("res/models/Plane.obj"));
	        renderer = new DeferredRenderer(800, 600);
        } catch (DogWoodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Entity collisionTestEntity = new Entity();

        ctTransform = new TransformComponent();
        ctTransform.setTranslation(0f, 1f, -1.7f);
        ctTransform.setScale(3, 3, 3);


        try {

            TexturedMeshComponent ctsprite = new TexturedMeshComponent(treeMesh, spriteSheet, treeShader);
            collisionTestEntity.addComponent(ctsprite);
            collisionTestEntity.addComponent(ctTransform);
            collisionTestEntity.addComponent(new CameraReferenceComponent(Scene.getCamera()));

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

     //   ctTransform.rotateEuler((float)Math.PI / 1000f, 0f, 0f);
        Scene.update(delta);
        renderer.beginDrawing();
        Scene.render(delta);
        renderer.endDrawing();
    }
}
