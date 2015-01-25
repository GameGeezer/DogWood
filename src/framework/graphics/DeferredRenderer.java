package framework.graphics;

import framework.graphics.opengl.ShaderProgram;
import framework.graphics.opengl.bufferObjects.AttachmentType;
import framework.graphics.opengl.OGLColorType;
import framework.graphics.opengl.Texture;
import framework.graphics.opengl.bufferObjects.FBO;
import framework.graphics.opengl.bufferObjects.RBO;
import framework.util.exceptions.GraphicsException;
import framework.util.fileIO.FileUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.IOException;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Will on 1/23/2015.
 */
public class DeferredRenderer {

    public static final int POSITION_BUFFER_BINDING = 0;
    public static final int NORMAL_BUFFER_BINDING = 1;
    public static final int DIFFUSE_BUFFER_BINDING = 2;
    public static final int SCREEN_BUFFER_BINDING = 3;

    private final Texture positionBuffer;
    private final Texture normalBuffer;
    private final Texture diffuseBuffer;

    private final Texture screenTexture;

    private final FBO fbo;
    private final RBO fboDepthBuffer;

    public DeferredRenderer(final int width, final int height) throws GraphicsException{

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE); // Enable later

        positionBuffer = new Texture(width, height, POSITION_BUFFER_BINDING, OGLColorType.RGBA16F);
        normalBuffer = new Texture(width, height, NORMAL_BUFFER_BINDING, OGLColorType.RGBA16F);
        diffuseBuffer = new Texture(width, height, DIFFUSE_BUFFER_BINDING, OGLColorType.RGBA16F);

        screenTexture = new Texture(width, height, SCREEN_BUFFER_BINDING, OGLColorType.RGBA8);

        fbo = new FBO();
        fboDepthBuffer = new RBO(width, height, AttachmentType.DEPTH_ATTACHMENT);

        fbo.bind();
        fboDepthBuffer.bind();

        positionBuffer.attachToFBO(AttachmentType.COLOR_ATTACHMENT0);
        normalBuffer.attachToFBO(AttachmentType.COLOR_ATTACHMENT1);
        diffuseBuffer.attachToFBO(AttachmentType.COLOR_ATTACHMENT2);

        final IntBuffer buffer = BufferUtils.createIntBuffer(4);
        buffer.put(0, AttachmentType.COLOR_ATTACHMENT0.ID);
        buffer.put(1, AttachmentType.COLOR_ATTACHMENT1.ID);
        buffer.put(2, AttachmentType.COLOR_ATTACHMENT2.ID);
        //buffer.put(specularBuffer.getUnit(), GL_COLOR_ATTACHMENT3);
        buffer.rewind();
        // Creates an array of buffers that the fragment shader will output to
        GL20.glDrawBuffers(buffer);

        if(!fbo.checkForErrors()) {
            throw new GraphicsException("Could not compile FBO");
        }
    }

    public void beginDrawing() {

        fbo.bind();

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        //fbo.unbind();
    }

    public void endDrawing() {

        fbo.unbind();

        positionBuffer.bind();
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 0.0f); // The bottom left corner

        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 0.0f); // The top left corner

        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(1.0f, 1.0f, 0.0f); // The top right corner

        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(1.0f, -1.0f, 0.0f); // The bottom right corner

        GL11.glEnd();

        positionBuffer.unbind();
    }

    public void destroy() {

        positionBuffer.destroy();
        normalBuffer.destroy();
        diffuseBuffer.destroy();
    }
}
