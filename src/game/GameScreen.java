package game;

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
import framework.util.fileIO.OBJLoader;
import framework.util.exceptions.DogWoodException;
import framework.util.fileIO.FileUtil;
import framework.util.fileIO.WavefrontLoader;
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

    public void onPause() {

    }

    public void onResume() {

        ShaderProgram treeShader = null;
        ShaderProgram shader2 = null;
        Image spriteSheet = null;
        Mesh treeMesh = null;
        Mesh lowPolyTree;

        try {
            Map<Integer, String> attributes = new HashMap<Integer, String>();
            attributes.put(0, "in_Position");
            attributes.put(1, "in_TextureCoord");

            treeShader = new ShaderProgram(FileUtil.readText("res/shaders/DeferredMeshShader.vert"), FileUtil.readText("res/shaders/DeferredMeshShader.frag"), attributes);
            shader2 = new ShaderProgram(FileUtil.readText("res/shaders/SpriteShader.vert"), FileUtil.readText("res/shaders/SpriteShader.frag"), attributes);
            spriteSheet = Image.loadPNG(new File("res/textures/walls128.png"), Image.ImageFormat.RGBA);

	        /// TODO ( ERIK, WILL ): Delete until models are needed
		        treeMesh = WavefrontLoader.LOADER.load(new File("res/models/Plane.obj"));
		        //final Mesh cube = OBJLoader.LOADER.loadModel ( "res/models/cube.obj" );
		       // final Mesh fern = OBJLoader.LOADER.loadModel ( "res/models/fern.obj" );
		       // lowPolyTree = OBJLoader.LOADER.loadModel ( "res/models/lowPolyTree.obj" );
		       // final Mesh tree = OBJLoader.LOADER.loadModel ( "res/models/tree.obj" );
		      //  if ( cube != null && fern != null && lowPolyTree != null && tree != null ) {
			   //     System.out.println ( "I'd say the loader works satisfactorily, wouldn't you?" );
               //     System.out.println ( "Looks totally topsbloobie man" );
		      ///  }
	        renderer = new DeferredRenderer(800, 600);
        } catch (DogWoodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Entity collisionTestEntity = new Entity();

        ctTransform = new TransformComponent();
        ctTransform.setTranslation(0f, 1f, -2f);
        ctTransform.setScale(3, 3, 3);

        //create sprite
        Entity spriteEntity = new Entity();

        Player player = new Player();

        Scene.addEntity(player);

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
