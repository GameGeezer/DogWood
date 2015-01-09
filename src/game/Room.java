package game;

import framework.util.exceptions.DogWoodException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Will on 1/5/2015.
 */
public class Room {

    private int width, height;
    private Map<String, int[][]> mapData = new HashMap<>();

    public Room(int width, int height) {

        this.width = width;
        this.height = height;
    }

    public void addLayer(String layerName) {

        mapData.put(layerName, new int[width][height]);
    }

    public void setLayer(int[] data) throws DogWoodException {

        if(data.length % width != 0) {

            throw new DogWoodException("Room data is not divisible by width");
        }
    }

    public void setLayerAt(String layerName, int x, int y, int value) {

        mapData.get(layerName)[x][y] = value;
    }
}
