package game;

import framework.IScreen;
import framework.graphics.Image;
import framework.graphics.Mesh;
import framework.graphics.Sprite;
import framework.graphics.opengl.*;
import framework.scene.Entity;
import framework.scene.components.TransformComponent;
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

    //private Camera camera = new Camera();
    private VAO vao;
    private ShaderProgram shader;
    private Sprite sprite;
    Mesh teapot = null;

    public void onPause() {

    }

    public void onResume() {
        Entity entity = new Entity();
        TransformComponent transform = new TransformComponent();
        transform.translate(10, 10, 10);
        //Sprite sprite = new Sprite();
       // EntityComponent renderComponent = new SpriteRenderComponent(positionComponent, scaleComponent, sprite);
        entity.addComponent(transform);
        //entity.addComponent(renderComponent);

        Map<Integer, String> attributes = new HashMap<Integer, String>();
        attributes.put(0, "in_Position");
        attributes.put(1, "in_Color");
        attributes.put(2, "in_TextureCoord");

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
        sprite = new Sprite(image, shader);

        /*
        Matrix4 matrix1 = new Matrix4();
        Matrix4 matrix2 = new Matrix4();
        Matrix4.multiply(matrix1, matrix2, matrix1);

        Orientation q1 = new Orientation();

        q1.setEuler((float)Math.PI / 2, (float)Math.PI / 2, 0 );
        System.out.println(q1.computeEulerAngles().getX());
        System.out.println(q1.computeEulerAngles().getZ());
        System.out.println(q1.computeRoll());
        System.out.println("Roll: " + Math.toDegrees(q1.computeRoll()));
        System.out.println("Pitch: " + Math.toDegrees(q1.computePitch()));
        System.out.println("Yaw: " + Math.toDegrees(q1.computeYaw()));
        */

        String[] components = "v1//vn1".split("/");
        System.out.println(components.length);
        System.out.println("v1//vn1".hashCode() == "v1//vn1".hashCode());
    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        //sprite.draw();
        shader.bind();
        teapot.draw();
        shader.unbind();
    }
}
