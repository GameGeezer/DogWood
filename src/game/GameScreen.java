package game;

import framework.Screen;
import framework.graphics.Camera;
import framework.graphics.Sprite;
import framework.graphics.opengl.*;
import framework.scene.Entity;
import framework.scene.components.EntityComponent;
import framework.scene.components.PositionComponent;
import framework.scene.components.ScaleComponent;
import framework.scene.components.SpriteRenderComponent;
import framework.util.FileUtil;
import framework.util.exceptions.GraphicsException;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
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
        PositionComponent positionComponent= new PositionComponent();
        ScaleComponent scaleComponent = new ScaleComponent();
        //Sprite sprite = new Sprite();
       // EntityComponent renderComponent = new SpriteRenderComponent(positionComponent, scaleComponent, sprite);
        entity.addComponent(positionComponent);
        entity.addComponent(scaleComponent);
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
        sprite = new Sprite(shader);
    }

    public void onLeave() {

    }

    public void onResize(int width, int height) {

    }

    public void update(int delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        sprite.draw();
    }
}
