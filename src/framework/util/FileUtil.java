package framework.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author William Gervasio
 */
public class FileUtil {

    /**
     * Parses a file for the text
     * @param fileLocation
     * @return The text as a String
     */
    public static String readText(String fileLocation) throws IOException{
        StringBuilder shaderString = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderString.append(line).append("\n");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new IOException("File: " + fileLocation + " was not found");
        } catch (IOException e) {
            throw new IOException("File: " + fileLocation + " could not be read");
        }

        return shaderString.toString();
    }
}