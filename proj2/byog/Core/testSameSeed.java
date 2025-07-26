package byog.Core;

import byog.TileEngine.TETile;

public class testSameSeed {
    public static void main(String[] args) {
        Game game = new Game();
        TETile[][] ws = game.playWithInputString("n529788084356903613s");
        System.out.println(TETile.toString(ws));

        System.out.println("-------------------------------");

        ws = game.playWithInputString("n529788084356903613s");
        System.out.println(TETile.toString(ws));
    }
}
