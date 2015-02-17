package framework.graphics.opengl.bufferObjects;

import org.lwjgl.opengl.GL15;

/**
 * Hints for OpenGL
 *
 * @author william gervasio
 */
@SuppressWarnings("UnusedDeclaration")
public enum BufferedObjectUsage {

    STATIC_DRAW(GL15.GL_STATIC_DRAW),
    STATIC_READ(GL15.GL_STATIC_READ),
    STATIC_COPY(GL15.GL_STREAM_DRAW),
    STREAM_DRAW(GL15.GL_STREAM_DRAW),
    STREAM_READ(GL15.GL_STREAM_READ),
    STREAM_COPY(GL15.GL_STREAM_COPY),
    DYNAMIC_DRAW(GL15.GL_DYNAMIC_DRAW),
    DYNAMIC_READ(GL15.GL_DYNAMIC_READ),
    DYNAMIC_COPY(GL15.GL_DYNAMIC_COPY);

    public final int ID;

    private BufferedObjectUsage(final int id) {

        ID = id;
    }
}
