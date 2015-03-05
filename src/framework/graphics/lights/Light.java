package framework.graphics.lights;

import framework.graphics.Color;
import framework.util.math.Vector3f;

/**
 * TODO
 * Created by Will on 2/2/2015.
 */
public class Light {

    private final Vector3f attenuation = new Vector3f();
    private final Color color;

    private Light(Color color) {

        this.color = color;
    }
}
