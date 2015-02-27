package framework.graphics;

/**
 *
 * @author William Gervasio
 *
 */

import framework.util.exceptions.DogWoodException;
import framework.util.fileIO.PNGDecoder;

import java.io.*;
import java.nio.ByteBuffer;

@SuppressWarnings("WeakerAccess")
public final class Image {

    @SuppressWarnings("UnusedDeclaration")
    public enum ImageFormat {

        RGB(3, PNGDecoder.Format.RGB), RGBA(4, PNGDecoder.Format.RGBA);

        public final int ELEMENT_COUNT;
        public final PNGDecoder.Format PNG_FORMAT;

        private ImageFormat(int elementCount, PNGDecoder.Format pngFormat) {

            ELEMENT_COUNT = elementCount;

            PNG_FORMAT = pngFormat;
        }
    }

    private final int width, height;
    private final ByteBuffer buffer;

    public Image(final int width, final int height, final ByteBuffer buffer) {

        this.width = width;
        this.height = height;
        this.buffer = buffer;
    }

    public static Image loadPNG(final File file, final ImageFormat format) throws DogWoodException {

        ByteBuffer buffer;
        int width, height;

        try {

            final InputStream is = new FileInputStream(file);
            PNGDecoder decoder = new PNGDecoder(is);

            buffer = ByteBuffer.allocateDirect(format.ELEMENT_COUNT * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buffer, decoder.getWidth() * format.ELEMENT_COUNT, format.PNG_FORMAT);
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

    public final int getWidth() {

        return width;
    }

    public final int getHeight() {

        return height;
    }

    public final ByteBuffer getBuffer() {

        return buffer;
    }

}