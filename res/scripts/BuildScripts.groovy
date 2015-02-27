import framework.graphics.Image
import framework.graphics.opengl.ShaderProgram
import framework.scene.Entity
import framework.scene.StateStack
import framework.scene.components.UpdateComponent
import framework.scene.components.collision.BoxFixtureComponent
import framework.scene.components.collision.PhysicsBodyComponent
import framework.scene.components.general.CameraReferenceComponent
import framework.scene.components.TransformComponent
import framework.util.exceptions.EntityException
import framework.util.fileIO.FileUtil
import framework.util.math.Transform
import game.Scene
import framework.scene.components.graphics.SpriteComponent
import game.components.player.DogUpdateComponent
import game.components.player.PlayerUpdateComponent
import game.states.DecelerateMovementState
import game.states.MovementState
import game.states.TimedMovementState
import game.states.WalkMovementState
import groovy.json.JsonSlurper
import org.jbox2d.dynamics.BodyType

/**
 *@author William Gervasio
 */

public Entity buildPlayer(Transform transform) {

    Entity entity = new Entity();

    TransformComponent transformComponent = new TransformComponent(transform);

    Image playerImage = Image.loadPNG(new File("res/textures/walkingSprite1.png"), Image.ImageFormat.RGBA);

    String vertexShader = FileUtil.readText("res/shaders/DeferredSpriteShader.vert");
    String fragmentShader = FileUtil.readText("res/shaders/DeferredSpriteShader.frag");

    Map<Integer, String> attributes = new HashMap<>();
    attributes.put(0, "in_Position");
    attributes.put(1, "in_Normal");
    attributes.put(2, "in_TextureCoord");

    ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader, attributes)

    SpriteComponent spriteComponent = new SpriteComponent(playerImage, shader, 3, 4);

    PhysicsBodyComponent bodyComponent = new PhysicsBodyComponent(BodyType.DYNAMIC);

    BoxFixtureComponent fixtureComponent = new BoxFixtureComponent(0.1f, 0.02f, 0, 0, 0);

    entity.addComponent(spriteComponent);
    entity.addComponent(transformComponent);
    entity.addComponent(new CameraReferenceComponent(Scene.getCamera()));
    entity.addComponent(new PlayerUpdateComponent());
    entity.addComponent(bodyComponent);
    entity.addComponent(fixtureComponent);

    return entity;
}

public Entity buildTestEnemy(Transform transform, Entity master) {

    Entity entity = new Entity();

    TransformComponent transformComponent = new TransformComponent(transform);

    Image playerImage = Image.loadPNG(new File("res/textures/dog.png"), Image.ImageFormat.RGBA);

    String vertexShader = FileUtil.readText("res/shaders/DeferredSpriteShader.vert");
    String fragmentShader = FileUtil.readText("res/shaders/DeferredSpriteShader.frag");

    Map<Integer, String> attributes = new HashMap<>();
    attributes.put(0, "in_Position");
    attributes.put(1, "in_Normal");
    attributes.put(2, "in_TextureCoord");

    ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader, attributes)

    SpriteComponent spriteComponent = new SpriteComponent(playerImage, shader, 1, 1);

    PhysicsBodyComponent bodyComponent = new PhysicsBodyComponent(BodyType.DYNAMIC);

    BoxFixtureComponent fixtureComponent = new BoxFixtureComponent(0.1f, 0.02f, 0, 0, 0);

    UpdateComponent updateComponent = new DogUpdateComponent(master);

    entity.addComponent(updateComponent);
    entity.addComponent(spriteComponent);
    entity.addComponent(transformComponent);
    entity.addComponent(new CameraReferenceComponent(Scene.getCamera()));
    entity.addComponent(bodyComponent);
    entity.addComponent(fixtureComponent);

    return entity;
}

public Entity buildTree(Transform transform) {

    Entity entity = new Entity();

    TransformComponent transformComponent = new TransformComponent(transform);

    Image playerImage = Image.loadPNG(new File("res/textures/tree1.png"), Image.ImageFormat.RGBA);

    String vertexShader = FileUtil.readText("res/shaders/DeferredSpriteShader.vert");
    String fragmentShader = FileUtil.readText("res/shaders/DeferredSpriteShader.frag");

    Map<Integer, String> attributes = new HashMap<>();
    attributes.put(0, "in_Position");
    attributes.put(1, "in_Normal");
    attributes.put(2, "in_TextureCoord");

    ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader, attributes)

    SpriteComponent spriteComponent = new SpriteComponent(playerImage, shader, 1, 1);

    PhysicsBodyComponent bodyComponent = new PhysicsBodyComponent(BodyType.STATIC);

    BoxFixtureComponent fixtureComponent = new BoxFixtureComponent(0.03f, 0.02f, 0, -0.15f, 0);
    entity.addComponent(spriteComponent);
    entity.addComponent(transformComponent);
    entity.addComponent(new CameraReferenceComponent(Scene.getCamera()));
    entity.addComponent(bodyComponent);
    entity.addComponent(fixtureComponent);

    return entity;
}

public void buildTileMap() {
    JsonSlurper slurper = new JsonSlurper()
    def json = slurper.parse(file)
    json.height
}