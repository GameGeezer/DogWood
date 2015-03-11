import framework.graphics.Image
import framework.graphics.TextureAtlas
import framework.graphics.opengl.ShaderProgram
import framework.scene.Entity
import framework.scene.StateStack
import framework.scene.components.UpdateComponent
import framework.scene.components.collision.BoxFixtureComponent
import framework.scene.components.collision.PhysicsBodyComponent
import framework.scene.components.general.CameraReferenceComponent
import framework.scene.components.TransformComponent
import framework.scene.components.graphics.TileMapComponent
import framework.util.Grid2D
import framework.util.exceptions.EntityException
import framework.util.fileIO.FileUtil
import framework.util.math.Transform
import game.Scene
import framework.scene.components.graphics.SpriteComponent
import game.components.player.DogUpdateComponent
import game.components.player.NetSensorUpdateComponent
import game.components.player.PlayerUpdateComponent
import game.states.DecelerateMovementState
import game.states.MovementState
import game.states.TimedMovementState
import game.states.WalkMovementState
import groovy.json.JsonSlurper
import org.jbox2d.dynamics.BodyType
import org.jbox2d.dynamics.Filter

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

public Entity buildPlayerNetSensor(Entity player) {

    Entity entity = new Entity();

    Image playerImage = Image.loadPNG(new File("res/textures/blockImage.png"), Image.ImageFormat.RGBA);

    String vertexShader = FileUtil.readText("res/shaders/DeferredSpriteShader.vert");
    String fragmentShader = FileUtil.readText("res/shaders/DeferredSpriteShader.frag");

    Map<Integer, String> attributes = new HashMap<>();
    attributes.put(0, "in_Position");
    attributes.put(1, "in_Normal");
    attributes.put(2, "in_TextureCoord");

    ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader, attributes)

    SpriteComponent spriteComponent = new SpriteComponent(playerImage, shader, 1, 1);

    Transform tree4Transform = new Transform();
    tree4Transform.setTranslation(0f, 0, -1.85f);
    //tree4Transform.rotateEuler((float)Math.PI / 2.5f, 0f, 0f);
    tree4Transform.setScale(0.25f, 0.25f, 0.25f);

    TransformComponent transformComponent = new TransformComponent(tree4Transform);

    PhysicsBodyComponent bodyComponent = new PhysicsBodyComponent(BodyType.DYNAMIC);

    BoxFixtureComponent fixtureComponent = new BoxFixtureComponent(0.03f, 0.02f, 0, -0.15f, 0, true, new Filter());

    NetSensorUpdateComponent updateComponent = new NetSensorUpdateComponent(player)

    entity.addComponent(spriteComponent);
    entity.addComponent(transformComponent);
    entity.addComponent(bodyComponent);
    entity.addComponent(fixtureComponent);
    entity.addComponent(new CameraReferenceComponent(Scene.getCamera()));
    entity.addComponent(updateComponent);

    return entity;
}


public Entity buildTileMap() {

    Image playerImage = Image.loadPNG(new File("res/textures/HighResTiles.png"), Image.ImageFormat.RGBA);

    TextureAtlas atlas = new TextureAtlas(playerImage, 128, 128);

    String vertexShader = FileUtil.readText("res/shaders/DeferredMeshShader.vert");
    String fragmentShader = FileUtil.readText("res/shaders/DeferredMeshShader.frag");

    Map<Integer, String> attributes = new HashMap<>();
    attributes.put(0, "in_Position");
    attributes.put(1, "in_Normal");
    attributes.put(2, "in_TextureCoord");

    ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader, attributes)

    Grid2D<Integer> map = new Grid2D<Integer>(50, 50);

    Random r = new Random();
    for(int x = 0; x < map.getLength(); ++x) {

        for(int y = 0; y < map.getHeight(); ++y) {

            map.set(x, y, r.nextInt(16));
        }
    }

    TileMapComponent tileMapComponent = new TileMapComponent(shader, map, atlas);

    Transform transform = new Transform();
    transform.setTranslation(0f, 0, -1.85f);
    //tree4Transform.rotateEuler((float)Math.PI / 2.5f, 0f, 0f);
    transform.setScale(0.25f, 0.25f, 0.25f);

    Entity entity = new Entity();

    entity.addComponent(tileMapComponent);
    entity.addComponent(new TransformComponent(transform));
    entity.addComponent(new CameraReferenceComponent(Scene.getCamera()));

    return entity;
}