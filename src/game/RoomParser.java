package game;

/**
 * @author William Gervasio
 */
public class RoomParser {

    public void parse(String roomText) {

        String[] horizontalSlices = roomText.split("\n");

        for (int i = 0; i < horizontalSlices.length; ++i) {

            String[] tiles = horizontalSlices[i].split(",");

            for (int j = 0; j < tiles.length; ++j) {

            }
        }
    }
}
