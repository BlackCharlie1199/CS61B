package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * Generate a world based on the map parameter
 */
public class WorldGenerator {
    private static TETile[][] world;
    /* 
        I store the upper and lower bound of x and y in this linked list
        for example,
        ((1,2,3,4)) means there is a room whose bottom left corner is (1,2) and upper right corner is (3,4)
    */
    private static LinkedList<LinkedList<Integer>> existedRoomPosition = new LinkedList<>();
    
    /**
     * Generate a world according to the map parameter
     * @param mpg
     * mpg is a MapParameterGenerator class
     */
    public static void generate(MapParameterGenerator mpg) {
        world = new TETile[mpg.WIDTH][mpg.HEIGHT];
        initialize(mpg);
        drawRoom(mpg);
        drawHallWays(mpg);
    }

    /**
     * @return
     * return the world we generated
     */
    public static TETile[][] getWorld() {
        return world;
    }

    // initialize the map with nothing
    private static void initialize(MapParameterGenerator mpg) {
       for(int x = 0; x < mpg.WIDTH; x++) {
          for(int y = 0; y < mpg.HEIGHT; y++) {
              world[x][y] = Tileset.NOTHING;
          }
       }
    }

    // draw the room
    private static void drawRoom(MapParameterGenerator mpg) {
        int i = 0;
        while(i < mpg.roomNumber) {
            int width = Math.abs(mpg.RANDOM.nextInt()) % 7 + 9;
            int height = Math.abs(mpg.RANDOM.nextInt()) % 10 + 5;
            int startX = Math.abs(mpg.RANDOM.nextInt()) % (mpg.WIDTH - width);
            int startY = Math.abs(mpg.RANDOM.nextInt()) % (mpg.HEIGHT - height);
            if (positionValid(startX, startY, width, height)) {
                drawRectangle(startX, startY, width, height);
                i++;
            }
        }
    }

    // check whether the position you want to draw the room occupied if not, add it to the existedRoomPosition
    private static boolean positionValid(int x, int y, int width, int height)  {
        for(LinkedList<Integer> i: existedRoomPosition) {
            if ((x >= i.get(0) && x <= i.get(2)) || (x + width >= i.get(0) && x + width<= i.get(2))) {
                if (!(y >= i.get(3) || y + height <= i.get(1))) {
                    return false;
                }
            }
        }
        LinkedList<Integer> addedRoom = new LinkedList<>();
        for(int j = 0; j < 2; j++) {
            addedRoom.addLast(x + width * j);
            addedRoom.addLast(y + height* j);
        }
        existedRoomPosition.addLast(addedRoom);
        return true;
    }

    // draw rectangle
    private static void drawRectangle(int x, int y, int width, int height) {
        // draw the wall first
        for(int i = 0; i < 2; i++) {
            drawHorizontal(x, y + i * height, width, Tileset.WALL);
            drawVertical(x + i * width, y, height, Tileset.WALL);
        }

        // filled the rectangle
        for(int i = 1; i < width; i++) {
            drawVertical(x + i, y + 1, height - 2, Tileset.FLOOR);
        }
    }

    private static void drawHorizontal(int x, int y, int width, TETile type) {
        for(int i = 0; i <= width; i++) {
            if (!world[x+i][y].description().equals("floor")) {
                world[x+i][y] = type;
            }
        }
    }

    private static void drawVertical(int x, int y, int height, TETile type) {
        for(int i = 0; i <= height; i++) {
            if (!world[x][y+i].description().equals("floor")) {
                world[x][y+i] = type;
            }
        }
    }

    private static void drawHallWays(MapParameterGenerator mpg) {
        for(int i = 0; i < mpg.roomNumber; i++) {
            LinkedList<Integer> originRoom = existedRoomPosition.get(i);
            LinkedList<Integer> destinyRoom = existedRoomPosition.get((i + 1) % mpg.roomNumber);
            int originMidX = (originRoom.get(0) + originRoom.get(2)) / 2;
            int originMidY = (originRoom.get(1) + originRoom.get(3)) / 2;
            int destinyMidX = (destinyRoom.get(0) + destinyRoom.get(2)) / 2;
            int destinyMidY = (destinyRoom.get(1) + destinyRoom.get(3)) / 2;

            int len = Math.abs(destinyMidX - originMidX);
            int realStartPoint = Math.min(originMidX, destinyMidX);

            drawHorizontal(realStartPoint, originMidY - 1, len, Tileset.WALL);
            drawHorizontal(realStartPoint, originMidY, len, Tileset.FLOOR);
            drawHorizontal(realStartPoint, originMidY + 1, len, Tileset.WALL);

            len = Math.abs(destinyMidY - originMidY);
            realStartPoint = Math.min(originMidY, destinyMidY);

            drawVertical(destinyMidX + 1, realStartPoint, len, Tileset.WALL);
            drawVertical(destinyMidX, realStartPoint, len, Tileset.FLOOR);
            drawVertical(destinyMidX- 1, realStartPoint, len, Tileset.WALL);
        }
    }
}
