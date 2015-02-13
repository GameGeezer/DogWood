import framework.graphics.Image
import framework.graphics.opengl.ShaderProgram
import framework.scene.Entity
import framework.scene.components.collision.BoxFixtureComponent
import framework.scene.components.collision.PhysicsBodyComponent
import framework.scene.components.general.CameraReferenceComponent
import framework.scene.components.TransformComponent
import framework.util.fileIO.FileUtil
import framework.util.math.Transform
import game.Scene
import game.components.SpriteComponent
import game.components.player.PlayerControllerComponent
import game.components.player.PlayerUpdateComponent
import org.jbox2d.dynamics.BodyType

/**
 * Created by Will on 2/9/2015.
 */

class BasicBulletBuilder {

    class BasicBullet extends  Entity {

    }

    private String vertexShader = FileUtil.readText("res/shaders/DeferredSpriteShader.vert");
    private String fragmentShader = FileUtil.readText("res/shaders/DeferredSpriteShader.frag");
    private Image playerImage = Image.loadPNG(new File("res/textures/walkingSprite1.png"), Image.ImageFormat.RGBA);

    public Entity getBasicBullet(float x, float y) {


    }

    public void BasicBullet(BasicBullet bullet) {

    }

    private BasicBullet buildBullet() {

        Entity entity = new Entity();

        TransformComponent transformComponent = new TransformComponent();
        transformComponent.setTranslation(0, 0, -1.2f);
        transformComponent.rotateEuler((float)Math.PI / 2.5f, 0f, 0f);
        transformComponent.setScale(0.25f, 0.25f, 0);

        Map<Integer, String> attributes = new HashMap<>();
        attributes.put(0, "in_Position");
        attributes.put(1, "in_Normal");
        attributes.put(2, "in_TextureCoord");

        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader, attributes)

        SpriteComponent spriteComponent = new SpriteComponent(playerImage, shader, 3, 4);

        PhysicsBodyComponent bodyComponent = new PhysicsBodyComponent(BodyType.DYNAMIC, 0, 1.5f);

        BoxFixtureComponent fixtureComponent = new BoxFixtureComponent(0.1f, 0.02f, 0, 0, 0);

        entity.addComponent(spriteComponent);
        entity.addComponent(transformComponent);
        entity.addComponent(new CameraReferenceComponent(Scene.getCamera()));
        entity.addComponent(new PlayerControllerComponent());
        entity.addComponent(new PlayerUpdateComponent());
        entity.addComponent(bodyComponent);
        entity.addComponent(fixtureComponent);

        return entity;
    }
}
