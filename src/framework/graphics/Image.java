package framework.graphics;

/**
 *
 * @author William Gervasio
 *
 */

import framework.util.PNGDecoder;
import framework.util.exceptions.DogWoodException;

import java.io.*;
import java.nio.ByteBuffer;

public class Image {

    private int width, height;
    private ByteBuffer buffer;

    public Image(int width, int height, ByteBuffer buffer) {
        this.width = width;
        this.height = height;
        this.buffer = buffer;
    }

    public static Image loadPNG(File file) throws DogWoodException {
        ByteBuffer buffer;
        int width, height;
        try {
            InputStream is = new FileInputStream(file);
            PNGDecoder decoder = new PNGDecoder(is);

            buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            width = decoder.getWidth();
            height = decoder.getHeight();

            buffer.flip();

            is.close();
        } catch (FileNotFoundException e) {
            throw new DogWoodException("File: " + file.getAbsolutePath() + " could not be found.");
        } catch (IOException e) {
            throw new DogWoodException("File: " + file.getAbsolutePath() + " could not be read.");
        }

        return new Image(width, height, buffer);
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