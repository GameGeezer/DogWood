package framework.graphics.opengl;

import framework.util.exceptions.GraphicsException;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

/**
 * ShaderPrograms manage shaders for the opengl pipeline.
 *
 * @author William Gervasio
 */
public final class ShaderProgram {

    private final int handle;


    /**
     * Create a shader with vertex shader and fragment shader stages.
     *
     * @param vertexShader   The vertex shader source
     * @param fragmentShader The fragment shader source
     * @param attributes
     * @throws GraphicsException
     */
    public ShaderProgram(String vertexShader, String fragmentShader, Map<Integer, String> attributes) throws GraphicsException {

        // Create the shader and grab the handle
        handle = GL20.glCreateProgram();

        // Compile the vertex and fragment shaders then grab their handles. Throw an exception if they can't be compiled
        int vertexHandle = compileShader(vertexShader, GL20.GL_VERTEX_SHADER);
        int fragmentHandle = compileShader(fragmentShader, GL20.GL_FRAGMENT_SHADER);

        // Attach the the vertex and fragment shaders to the program.
        GL20.glAttachShader(handle, vertexHandle);
        GL20.glAttachShader(handle, fragmentHandle);

        // Bind all of the attributes of name(value) to a location(Key)
        for (Map.Entry<Integer, String> e : attributes.entrySet()) {

            GL20.glBindAttribLocation(handle, e.getKey(), e.getValue());
        }

        // Try and link the program
        GL20.glLinkProgram(handle);

        // Check if there was any errors while linking
        if (checkForLinkError(handle))
            throw new GraphicsException("Failed to link shader");


        // Now that the program is created we can free memory
        GL20.glDetachShader(handle, vertexHandle);
        GL20.glDetachShader(handle, fragmentHandle);
        GL20.glDeleteShader(vertexHandle);
        GL20.glDeleteShader(fragmentHandle);
    }

    /**
     * Create a ShaderProgram with vertex, fragment, and geometry stages.
     *
     * @param vertexShader   The vertex shader source
     * @param fragmentShader The fragment shader source
     * @param geometryShader The geometry shader source
     * @param attributes
     * @throws GraphicsException
     */
    public ShaderProgram(String vertexShader, String fragmentShader, String geometryShader, Map<Integer, String> attributes) throws GraphicsException {

        // Create the shader and grab the handle
        handle = GL20.glCreateProgram();

        // Compile the vertex, fragment, and geometry shaders then grab their handles. Throw an exception if they can't be compiled
        int vertexHandle = compileShader(vertexShader, GL20.GL_VERTEX_SHADER);
        int fragmentHandle = compileShader(fragmentShader, GL20.GL_FRAGMENT_SHADER);
        int geometryHandle = compileShader(geometryShader, GL32.GL_GEOMETRY_SHADER);

        GL20.glAttachShader(handle, vertexHandle);
        GL20.glAttachShader(handle, fragmentHandle);
        GL20.glAttachShader(handle, geometryHandle);

        // Bind all of the attributes of name(value) to a location(Key)
        for (Map.Entry<Integer, String> e : attributes.entrySet()) {
            GL20.glBindAttribLocation(handle, e.getKey(), e.getValue());
        }

        // Try and link the program
        GL20.glLinkProgram(handle);

        // Check if there was any errors while linking
        if (checkForLinkError(handle))
            throw new GraphicsException("Failed to link shader");

        // Now that the program is created we can free memory
        GL20.glDetachShader(handle, vertexHandle);
        GL20.glDetachShader(handle, fragmentHandle);
        GL20.glDetachShader(handle, geometryHandle);
        GL20.glDeleteShader(vertexHandle);
        GL20.glDeleteShader(fragmentHandle);
        GL20.glDeleteShader(geometryHandle);
    }

    /**
     * Bind the shader
     */
    public final void bind() {
        GL20.glUseProgram(handle);
    }

    /**
     * Unbind the shader
     */
    public final void unbind() {
        GL20.glUseProgram(0);
    }

    /**
     * Delete the shader
     */
    public final void destroy() {
        GL20.glDeleteProgram(handle);
    }

    /**
     * Finds the location of a uniform
     *
     * @param uniform
     * @return
     */
    public final int getUniformLocation(String uniform) {
        return GL20.glGetUniformLocation(handle, uniform);
    }

    /**
     * Compiles a vertex, fragment, or geometry shader
     *
     * @param shader
     * @param type
     * @return A handle to the compiled shader
     * @throws GraphicsException
     */
    private final int compileShader(String shader, int type) throws GraphicsException {

        int handle = GL20.glCreateShader(type);

        // Link the source
        GL20.glShaderSource(handle, shader);
        // Compile the shader
        GL20.glCompileShader(handle);

        // If the shader failed to compile throw an exception
        if (GL20.glGetShaderi(handle, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            switch (type) {
                case GL20.GL_VERTEX_SHADER:
                    throw new GraphicsException("Failed to compile vertex shader");
                case GL20.GL_FRAGMENT_SHADER:
                    throw new GraphicsException("Failed to compile fragment shader");
                case GL32.GL_GEOMETRY_SHADER:
                    throw new GraphicsException("Failed to compile geometry shader");
            }
        }

        return handle;
    }

    /**
     * Check to see if the shader was linked properly
     *
     * @param handle
     * @return True if properly linked
     */
    private final boolean checkForLinkError(int handle) {

        return GL20.glGetProgrami(handle, GL20.GL_LINK_STATUS) == GL11.GL_FALSE;
    }
}