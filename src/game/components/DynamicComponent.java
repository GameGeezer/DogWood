package game.components;

import framework.scene.Entity;
import framework.scene.components.UpdateComponent;
import framework.scene.components.util.TransformComponent;
import framework.util.RangeUtil;
import framework.util.math.Vector2;

import java.util.List;

/**
 * Created by Will on 1/13/2015.
 */
public class DynamicComponent extends UpdateComponent {

    private List<TransformComponent> transformComponents;

    private Vector2 velocity = new Vector2(), acceleration = new Vector2(), maxVelocity = new Vector2();

    public DynamicComponent(float maxVelocityX, float maxVelocityY) {

        maxVelocity.set(maxVelocityX, maxVelocityY);
    }

    @Override
    protected void onAttach() {

        transformComponents = (List<TransformComponent>) (List<?>) getParent().getComponentsOfType(TransformComponent.class);
    }

    @Override
    protected void onDetach() {

    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

    }

    @Override
    public void update(int delta) {

        float deltaOverAThousand = delta / 1000f;

        velocity.add(acceleration.getX() * deltaOverAThousand, acceleration.getY() * deltaOverAThousand);

        velocity.setX(RangeUtil.forceIntoRange(velocity.getX(), -maxVelocity.getX(), maxVelocity.getX()));
        velocity.setY(RangeUtil.forceIntoRange(velocity.getY(), -maxVelocity.getY(), maxVelocity.getY()));

        TransformComponent transformComponent = transformComponents.get(0);

        transformComponent.translate(velocity.getX() * deltaOverAThousand, velocity.getY() * deltaOverAThousand, 0);
    }

    public void setVelocity(float x, float y) {

        velocity.set(x, y);
    }

    public void setVelocityX(float x) {

        velocity.setX(x);
    }

    public void setVelocity(float y) {

        velocity.setY(y);
    }

    public void setAcceleration(float x, float y) {

        acceleration.set(x, y);
    }

    public void setAccelerationX(float x) {

        acceleration.setX(x);
    }

    public void setAccelerationY(float y) {

        acceleration.setY(y);
    }

    public void accelerate(float x, float y) {

        acceleration.add(x, y);
    }

    public float getVelocityX() {

        return velocity.getX();
    }

    public float getVelocityY() {

        return velocity.getY();
    }

    public float getAccelerationX() {

        return acceleration.getX();
    }

    public float getAccelerationY() {

        return acceleration.getY();
    }
}
