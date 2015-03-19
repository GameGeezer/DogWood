package framework.graphics.opengl;

import org.lwjgl.opengl.GL33;

import java.util.Map;

/**
 * @author William Gervasio
 */
public class Sampler {

    private final int handle;

    private final int[] textureUnits;
    private final Map<Integer, Integer> parameters;

    public Sampler(Map<Integer, Integer> parameters, int... textureUnits) {

        this.textureUnits = textureUnits;
        this.parameters = parameters;

        handle = GL33.glGenSamplers();

        for (final int key : parameters.keySet())
            GL33.glSamplerParameteri(handle, key, parameters.get(key));
    }

    public void bind() {

        for (final int unit : textureUnits) {

            GL33.glBindSampler(unit, handle);
        }
    }

    public void destroy() {

        GL33.glDeleteSamplers(handle);
    }
}