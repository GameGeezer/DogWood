package game;

import framework.Screen;
import framework.graphics.Image;
import framework.graphics.Sprite;
import framework.graphics.opengl.*;
import framework.scene.Entity;
import framework.scene.components.TransformComponent;
import framework.util.FileUtil;
import framework.util.exceptions.DogWoodException;
import framework.util.exceptions.GraphicsException;
import framework.util.math.Matrix4;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author William Gervasio
 */
public class GameScreen implements Screen {

    //private Camera camera = new Camera();
    private VAO vao;
    private ShaderProgram shader;
    private Sprite sprite;

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
            shader = new ShaderProgram(FileUtil.readText("res/TestShader.vert"), FileUtil.readText("res/TestShader.frag"), attributes);
        } catch (GraphicsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = null;
        try {
            image = Image.loadPNG(new File("res/textures/poulpi.png"));
            image.getWidth();
        } catch (DogWoodException e) {

        }
        System.out.println(image.getWidth());
        System.out.println(image.getHeight());
        System.out.println(image.getBuffer());
        sprite = new Sprite(image, shader);

        Matrix4 matrix1 = new Matrix4();
        Matrix4 matrix2 = new Matrix4();
        Matrix4.multiply(matrix1, matrix2, matrix1);
        System.out.println(matrix1.toString());
    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        //sprite.draw();
    }
}
