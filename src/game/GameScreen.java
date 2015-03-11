package game;

import engine.voxelitemedit.CubicMeshExtractor;
import framework.graphics.DeferredRenderer;
import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.components.TransformComponent;
import framework.scene.components.general.CameraReferenceComponent;
import framework.scene.components.graphics.TexturedMeshComponent;
import framework.util.Grid3D;
import framework.util.GridUtil;
import framework.util.exceptions.DogWoodException;
import framework.util.fileIO.FileUtil;
import framework.util.fileIO.WavefrontLoader;
import framework.util.math.Transform;
import framework.window.Application;
import framework.window.Screen;
import groovy.lang.Script;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author William Gervasio
 */
public class GameScreen implements Screen {

    private DeferredRenderer renderer;
    TransformComponent ctTransform;
    Entity player, enemy, tree;

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
            spriteSheet = Image.loadPNG(new File("res/textures/blockImage.png"), Image.ImageFormat.RGBA);
            Transform playerTransform = new Transform();
            playerTransform.setTranslation(0, 0, -1.5f);
            playerTransform.rotateEuler((float)Math.PI / 2.5f, 0f, 0f);
            playerTransform.setScale(0.25f, 0.25f, 0);

            Transform dogTransform = new Transform();
            dogTransform.setTranslation(0, 0, -1.55f);
            dogTransform.rotateEuler((float)Math.PI / 2.5f, 0f, 0f);
            dogTransform.setScale(0.2f, 0.2f, 0);

            Transform tree1Transform = new Transform();
            tree1Transform.setTranslation(0, 1, -1.05f);
            tree1Transform.rotateEuler((float)Math.PI / 2.5f, 0f, 0f);
            tree1Transform.setScale(1f, 1f, 0);

            Transform tree2Transform = new Transform();
            tree2Transform.setTranslation(-0.5f, 1, -1.05f);
            tree2Transform.rotateEuler((float)Math.PI / 2.5f, 0f, 0f);
            tree2Transform.setScale(1f, 1f, 0);

            Transform tree3Transform = new Transform();
            tree3Transform.setTranslation(0.5f, 1, -1.05f);
            tree3Transform.rotateEuler((float)Math.PI / 2.5f, 0f, 0f);
            tree3Transform.setScale(1f, 1f, 0);

            Transform tree4Transform = new Transform();
            tree4Transform.setTranslation(0.5f, 0, -1.05f);
            tree4Transform.rotateEuler((float)Math.PI / 2.5f, 0f, 0f);
            tree4Transform.setScale(1f, 1f, 0);

            Script s = Application.GROOVY_SHELL.parse( new File( "res/scripts/BuildScripts.groovy" ) );


            player = (Entity) s.invokeMethod("buildPlayer", playerTransform);
            enemy = (Entity) s.invokeMethod("buildTestEnemy", new Object[]{dogTransform, player});
            tree = (Entity) s.invokeMethod("buildTree", tree1Transform);
            Entity netSensor = (Entity) s.invokeMethod("buildPlayerNetSensor", player);

            Scene.addEntity(player);
            Scene.addEntity(enemy);
            Scene.addEntity(tree);
            Scene.addEntity((Entity) s.invokeMethod("buildTileMap", new Object[]{}));
            Scene.addEntity((Entity) s.invokeMethod("buildTree", tree2Transform));
            Scene.addEntity((Entity) s.invokeMethod("buildTree", tree3Transform));
            Scene.addEntity((Entity) s.invokeMethod("buildTree", tree4Transform));
            Scene.addEntity(netSensor);
            treeMesh = WavefrontLoader.LOADER.load(new File("res/models/Plane.obj"));
	        renderer = new DeferredRenderer(800, 600);

        } catch (DogWoodException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        /*
        Grid3D<Integer> testVoxelGrid = new Grid3D<>(50, 50, 5);
        Random r = new Random();
        for(int x = 0; x < testVoxelGrid.getLength(); ++x) {

            for(int y = 0; y < testVoxelGrid.getHeight(); ++y) {

                for(int z = 0; z < testVoxelGrid.getDepth(); ++z) {

                    testVoxelGrid.set(x, y, z, r.nextInt(2));
                }
            }
        }

        GridUtil.setEdges(testVoxelGrid, 0);

        Mesh voxelMesh = CubicMeshExtractor.extractDirty(testVoxelGrid);

        */
        Entity collisionTestEntity = new Entity();

        ctTransform = new TransformComponent();
        ctTransform.setTranslation(0f, 0f, -1.7f);
        ctTransform.setScale(0.25f, 0.25f, 0.25f);

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

    @Override
    public void onLeave() {

    }

    @Override
    public void onResize(int width, int height) {

    }

    @Override
    public void update(int delta) {

        // ctTransform.rotateEuler((float)Math.PI / 1000f, 0f, 0f);
        TransformComponent playerTransform = (TransformComponent) player.getComponentsOfType(TransformComponent.class).get(0);
        Scene.getCamera().setTranslation(-playerTransform.getX(), -playerTransform.getY() + 1f, -playerTransform.getZ() - 1);
        Scene.update(delta);
        renderer.beginDrawing();
        Scene.render(delta);
        renderer.endDrawing();
    }

    @Override
    public void onDestroy() {

    }
}
