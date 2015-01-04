package game;

import framework.IScreen;
import framework.scene.components.graphics.TexturedMeshComponent;
import framework.scene.components.util.CameraReferenceComponent;
import framework.scene.components.util.TransformComponent;
import framework.scene.uniforms.Camera;
import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.opengl.*;
import framework.scene.Entity;
import framework.util.fileIO.WavefrontLoader;
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
    private Camera camera = new Camera(800, 600, 0.1f, 100, 60);
    TransformComponent teapotTransform;

    public void onPause() {

    }

    public void onResume() {

        ShaderProgram shader = null;
        Image spriteSheet = null;
        Image playerImage = null;
        ShaderProgram meshShader = null;
        Mesh teapotMesh = null;
        try {
            Map<Integer, String> attributes = new HashMap<Integer, String>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_TextureCoord");

            shader = new ShaderProgram(FileUtil.readText("res/shaders/SpriteShader.vert"), FileUtil.readText("res/shaders/SpriteShader.frag"), attributes);
            meshShader = new ShaderProgram(FileUtil.readText("res/shaders/BasicShader.vert"), FileUtil.readText("res/shaders/BasicShader.frag"), attributes);
            spriteSheet = Image.loadPNG(new File("res/textures/walkingSprite1.png"));
            playerImage = Image.loadPNG(new File("res/textures/player.png"));
            WavefrontLoader meshLoader = new WavefrontLoader();
            teapotMesh = meshLoader.load(new File("res/models/utahTeapot.obj"));
        } catch(DogWoodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Entity meshEntity = new Entity();
        teapotTransform = new TransformComponent();
        teapotTransform.setTranslation(0, -0.5f, -3f);
        teapotTransform.setOrientationEuler(0, (float) Math.PI, 0);
        //teapotTransform.setScale(0.1f, 0.1f, 0.1f);

        //create sprite
        Entity spriteEntity = new Entity();

        TransformComponent spriteTransform = new TransformComponent();
        spriteTransform.setTranslation(0, -0.5f, -3f);
        spriteTransform.setOrientationEuler(0, (float) Math.PI, 0);
        spriteTransform.setScale(0.25f, 0.25f, 0.25f);

        try {
            meshEntity.addComponent(new TexturedMeshComponent(teapotMesh, playerImage, meshShader));
            meshEntity.addComponent(teapotTransform);
            meshEntity.addComponent(new CameraReferenceComponent(camera));

            SpriteComponent sprite = new SpriteComponent(spriteSheet, shader, 3, 4);
            spriteEntity.addComponent(sprite);
            spriteEntity.addComponent(spriteTransform);
            spriteEntity.addComponent(new CameraReferenceComponent(camera));
            spriteEntity.addComponent(new PlayerControllerComponent());
            spriteEntity.addComponent(new PlayerUpdateComponent());
            scene.addEntity(spriteEntity);
            scene.addEntity(meshEntity);
        } catch(DogWoodException e) {
            e.printStackTrace();
        }
    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {
        teapotTransform.rotateEuler(0, (float)Math.PI / 100 ,0);
        scene.update(delta);
        scene.render(delta);
    }
}
