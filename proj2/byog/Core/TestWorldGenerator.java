package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

public class TestWorldGenerator {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        long randomNum = StdRandom.uniform(12345567);
        MapParameterGenerator mpg= new MapParameterGenerator(randomNum);

        ter.initialize(mpg.WIDTH, mpg.HEIGHT);
        TETile[][] world = WorldGenerator.generate(mpg);
        ter.renderFrame(world);
    }
}
