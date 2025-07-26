package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.io.*;

public class Game implements Serializable {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        long seed;
        WorldStatus ws = new WorldStatus();
        input = input.toUpperCase();

        for(int i = 0; i < input.length(); i++) {
            char charAtI = input.charAt(i);
            if (charAtI == 'S') {
                finalWorldFrame = ws.getWorld();
                break;
            } else if (charAtI == 'N') {
                seed = getSeed(input, i + 1);
                i += String.valueOf(seed).length();
                MapParameterGenerator mpg = new MapParameterGenerator(seed);
                ws.registerMap(mapGenerator.generate(mpg));
                finalWorldFrame = ws.getWorld();
            } else if (charAtI == 'Q') {
                saveWorld(ws);
            } else if (charAtI == 'L'){
                ws = loadWorld();
                finalWorldFrame = ws.getWorld();
            }
        }
        return finalWorldFrame;
    }

    private long getSeed(String input, int index) {
        StringBuilder s = new StringBuilder();
        for(int i = index; i < input.length(); i++) {
            char num = input.charAt(i);
            if (num <= '9' && num >= '0') {
                s.append(num);
            } else {
                break;
            }
        }
        return Integer.parseInt(s.toString());
    }

    private void saveWorld(WorldStatus ws) {
        File f = new File("./saves/world.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(ws);
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    private WorldStatus loadWorld() {
        File f = new File("./saves/world.ser");
        try {
            if (f.exists()) {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                WorldStatus loadWorld = (WorldStatus) os.readObject();
                os.close();
                return loadWorld;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the save file!");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
            System.exit(0);
        }

        return new WorldStatus();
    }
}
