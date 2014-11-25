package framework.graphics;

/**
 *
 * @author William Gervasio
 *
 */

import java.nio.ByteBuffer;

public class Image {

    private int width, height;
    private ByteBuffer buffer;

    public Image(int width, int height, ByteBuffer buffer) {
        this.width = width;
        this.height = height;
        this.buffer = buffer;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

}