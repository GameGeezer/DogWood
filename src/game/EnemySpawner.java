package game;

import framework.scene.Entity;
import framework.scene.components.UpdateComponent;

/**
 * Created by Will on 1/13/2015.
 */
public class EnemySpawner extends UpdateComponent {

    private float min, max, time;

    public EnemySpawner(float min, float max, float time) {

        this.min = min;
        this.max = max;
        this.time = time;
    }

    @Override
    protected void onAttach() {

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

    }
}
