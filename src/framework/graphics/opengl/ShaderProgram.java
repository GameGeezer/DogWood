package framework.graphics.opengl;

import framework.util.exceptions.GraphicsException;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

import java.util.Map;

/**
 * @author William Gervasio
 */
public class ShaderProgram {

    private int handle;

    public ShaderProgram(String vertexShader, String fragmentShader, Map<Integer, String> attributes) throws GraphicsException {
        int vertexHandle, fragmentHandle;

        vertexHandle = compileShader(vertexShader, GL20.GL_VERTEX_SHADER);
        fragmentHandle = compileShader(fragmentShader, GL20.GL_FRAGMENT_SHADER);

        handle = GL20.glCreateProgram();

        GL20.glAttachShader(handle, vertexHandle);
        GL20.glAttachShader(handle, fragmentHandle);

        if (attributes != null) {
            for (Map.Entry<Integer, String> e : attributes.entrySet()) {
                GL20.glBindAttribLocation(handle, e.getKey(), e.getValue());
            }
        }

        GL20.glLinkProgram(handle);

        if (checkForLinkError(handle)) {
            throw new GraphicsException("Failed to link shader");
        }

        GL20.glDetachShader(handle, vertexHandle);
        GL20.glDetachShader(handle, fragmentHandle);
        GL20.glDeleteShader(vertexHandle);
        GL20.glDeleteShader(fragmentHandle);
    }

    public ShaderProgram(String vertexShader, String fragmentShader, String geometryShader, Map<Integer, String> attributes) throws GraphicsException {
        int vertexHandle, fragmentHandle, geometryHandle;

        vertexHandle = compileShader(vertexShader, GL20.GL_VERTEX_SHADER);
        fragmentHandle = compileShader(fragmentShader, GL20.GL_FRAGMENT_SHADER);
        geometryHandle = compileShader(geometryShader, GL32.GL_GEOMETRY_SHADER);

        handle = GL20.glCreateProgram();

        GL20.glAttachShader(handle, vertexHandle);
        GL20.glAttachShader(handle, fragmentHandle);
        GL20.glAttachShader(handle, geometryHandle);

        if (attributes != null) {
            for (Map.Entry<Integer, String> e : attributes.entrySet()) {
                GL20.glBindAttribLocation(handle, e.getKey(), e.getValue());
            }
        }

        GL20.glLinkProgram(handle);

        if (checkForLinkError(handle)) {
            throw new GraphicsException("Failed to link shader");
        }

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
    public void bind() {
        GL20.glUseProgram(handle);
    }

    /**
     * Unbind the shader
     */
    public void unbind() {
        GL20.glUseProgram(0);
    }

    /**
     * Delete the shader
     */
    public void destroy() {
        GL20.glDeleteProgram(handle);
    }

    /**
     * Finds the location of a uniform
     * @param uniform
     * @return
     */
    public int getUniformLocation(String uniform) {
        return GL20.glGetUniformLocation(handle, uniform);
    }

    /**
     * Compiles a vertex, fragment, or geometry shader
     * @param shader
     * @param type
     * @return A handle to the compiled shader
     * @throws GraphicsException
     */
    private int compileShader(String shader, int type) throws GraphicsException {
        int handle = GL20.glCreateShader(type);
        GL20.glShaderSource(handle, shader);
        GL20.glCompileShader(handle);

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
     * Makes sure the shader was linked preopperly
     * @param handle
     * @return True if propperly linked
     */
    private boolean checkForLinkError(int handle) {
        return GL20.glGetProgrami(handle, GL20.GL_LINK_STATUS) == GL11.GL_FALSE;
    }
}