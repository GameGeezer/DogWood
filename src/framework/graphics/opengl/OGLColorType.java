package framework.graphics.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL30;

/**
 * @author William Gervasio
 */
@SuppressWarnings("UnusedDeclaration")
public enum OGLColorType {

    RGB(GL11.GL_RGB),
    RGB4(GL11.GL_RGB4),
    RGB5(GL11.GL_RGB5),
    RGB8(GL11.GL_RGB8),
    RGB10(GL11.GL_RGB10),
    RGB12(GL11.GL_RGB12),
    RGB16(GL11.GL_RGB16),

    RGBA(GL11.GL_RGBA),
    RGBA2(GL11.GL_RGBA2),
    RGBA4(GL11.GL_RGBA4),
    RGBA8(GL11.GL_RGBA8),
    RGBA12(GL11.GL_RGBA12),
    RGBA16(GL11.GL_RGBA16),
    RGBA16F(GL30.GL_RGBA16F),

    BGR(GL12.GL_BGR),
    BGRA(GL12.GL_BGRA);

    public final int ID;

    private OGLColorType(int id) {

        ID = id;
    }
}
