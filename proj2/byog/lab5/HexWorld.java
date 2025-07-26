package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    // Use a private static class to store the position
    public static class Pos {
        private int x;
        private int y;
        private Pos(int a, int b) {
            x = a;
            y = b;
        }
    }

    // A helper method filling one line of flower
    private static void filledFlower(TETile[][] tiles, Pos p, int len) {
        for (int i = 0; i < len; i++) {
            tiles[p.x + i][p.y] = Tileset.FLOWER;
        }
    }
    /**
     * Draws a hexagon word with side length len, starting from (posX, posY) specifying the lower left corner
     */
    public static void addHexagon(TETile[][] tiles, Pos p, int len) {
        for (int i = 0; i < len; i++) {
            Pos drawPosition = new Pos(p.x + len - i -1, p.y + i);
            int drawQuantities = len + 2 * i;
            int upperY = p.y + len * 2 - 1;

            // Draw the lower line
            filledFlower(tiles, drawPosition, drawQuantities);

            // Draw the upper line
            drawPosition.y = upperY - i;
            filledFlower(tiles, drawPosition, drawQuantities);
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Pos p = new Pos(25, 10);
        for(int i = 0; i < 6; i++) {
            addHexagon(world, p, 3);
            p.y += 3 * 2;
        }

        // Draw the world
        ter.renderFrame(world);
    }
}
