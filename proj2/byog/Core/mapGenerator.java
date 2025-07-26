package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.LinkedList;
import java.util.Random;

/**
 * Generate a world based on the map parameter
 */
public class mapGenerator {
    private static TETile[][] world;
    /* 
        I store the upper and lower bound of x and y in this linked list
        for example,
        ((1,2,3,4)) means there is a room whose bottom left corner is (1,2) and upper right corner is (3,4)
    */
    private static LinkedList<Position> roomPosition = new LinkedList<>();

    /**
     * Generate a world according to the map parameter
     * @param mpg
     * mpg is a MapParameterGenerator class
     */
    public static TETile[][] generate(MapParameterGenerator mpg) {
        world = new TETile[mpg.WIDTH][mpg.HEIGHT];
        roomPosition = new LinkedList<>();
        initialize(mpg);
        drawRoom(mpg);
        drawHallWays(mpg);
        return world;
    }

    private static class Position {
        int leftX, downY, rightX, upY;

        private Position(int lx, int dy, int rx, int uy) {
            leftX = lx;
            downY = dy;
            rightX = rx;
            upY = uy;
        }

        private int getWidth() {
            return this.rightX - this.leftX;
        }

        private int getHeight() {
            return this.upY - this.downY;
        }

        private int getCenterX() {
            return (this.leftX + this.rightX) / 2;
        }

        private int getCenterY() {
            return (this.upY + this.downY) / 2;
        }

        private static int getDistance(Position self, Position tar) {
            int dx = self.leftX - tar.leftX;
            int dy = self.downY - tar.downY;
            return dx * dx + dy * dy;
        }
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
            int width = Math.abs(mpg.RANDOM.nextInt()) % 7 + 5;
            int height = Math.abs(mpg.RANDOM.nextInt()) % 10 + 4;
            int startX = Math.abs(mpg.RANDOM.nextInt()) % (mpg.WIDTH - width);
            int startY = Math.abs(mpg.RANDOM.nextInt()) % (mpg.HEIGHT - height);
            Position newRoom = new Position(startX, startY, startX + width, startY + height);
            if (positionValid(newRoom)) {
                drawRectangle(newRoom);
                roomPosition.addLast(newRoom);
                i++;
            }
        }
    }

    // check whether the position you want to draw the room occupied if not, add it to the roomPosition
    private static boolean positionValid(Position room)  {
        for(Position i: roomPosition) {
            if ((room.leftX >= i.leftX && room.leftX <= i.rightX) ||
                (room.rightX >= i.leftX && room.rightX <= i.rightX)) {
                if (!(room.downY >= i.upY || room.upY <= i.downY)) {
                    return false;
                }
            }
        }
        return true;
    }

    // draw rectangle
    private static void drawRectangle(Position room) {
        // draw the wall first
        int height = room.getHeight();
        int width = room.getWidth();
        for(int i = 0; i < 2; i++) {
            drawHorizontal(room.leftX, room.downY + i * height, width, Tileset.WALL);
            drawVertical(room.leftX + i * width, room.downY, height, Tileset.WALL);
        }

        // filled the rectangle
        for(int i = 1; i < width; i++) {
            drawVertical(room.leftX + i, room.downY + 1, height - 2, Tileset.FLOOR);
        }
    }

    private static void drawHorizontal(int x, int y, int width, TETile type) {
        // dir = 1 if width > 0 else -1
        int dir = 0;
        if (width != 0) {
            dir  = width / Math.abs(width);
        }
        for(int i = 0; i <= Math.abs(width); i++) {
            if (!world[x+dir*i][y].description().equals("floor")) {
                world[x+dir*i][y] = type;
            }
        }
    }

    private static void drawVertical(int x, int y, int height, TETile type) {
        int dir = 0;
        if (height != 0) {
            dir  = height / Math.abs(height);
        }
        for(int i = 0; i <= Math.abs(height); i++) {
            if (!world[x][y+dir*i].description().equals("floor")) {
                world[x][y+dir*i] = type;
            }
        }
    }

    private static void drawHallWays(MapParameterGenerator mpg) {
        int size = roomPosition.size();
        for(int i = 0; i < size - 1; i++) {
            Position self = roomPosition.removeFirst();
            Position minTar = findMinTar(self);

            drawLWays(self, minTar);
        }
    }

    private static Position findMinTar(Position self) {
        Position minTar = roomPosition.getFirst();
        double minDis = Position.getDistance(self, minTar);
        for (Position tar : roomPosition) {
            int dis = Position.getDistance(self, tar);
            if (minDis > dis) {
                minTar = tar;
                minDis = dis;
            }
        }
        return minTar;
    }

    private static void drawLWays(Position departure, Position destination) {
        int len = destination.getCenterX() - departure.getCenterX();
        int diff = len < 0 ? -1 : 1;
        drawHorizontal(departure.getCenterX(), departure.getCenterY() - 1, len + diff, Tileset.WALL);
        drawHorizontal(departure.getCenterX(), departure.getCenterY() , len , Tileset.FLOOR);
        drawHorizontal(departure.getCenterX(), departure.getCenterY() + 1, len + diff, Tileset.WALL);

        len = destination.getCenterY() - departure.getCenterY();
        drawVertical(destination.getCenterX() - 1, departure.getCenterY(), len, Tileset.WALL);
        drawVertical(destination.getCenterX(), departure.getCenterY() , len , Tileset.FLOOR);
        drawVertical(destination.getCenterX() + 1, departure.getCenterY(), len, Tileset.WALL);
    }
}
