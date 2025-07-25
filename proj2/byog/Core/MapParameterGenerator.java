package byog.Core;

import java.util.Map;
import java.util.Random;

public class MapParameterGenerator {
    public int WIDTH;
    public int HEIGHT;
    public Random RANDOM;
    public int roomNumber;
    public int hallWayNumber;

    /**
     * Generate a class containing necessary parameter to create a world
     * including WIDTH, HEIGHT, roomNumber, RANDOM which is a pseudorandomness
     * @param randomNumber
     * randomNumber is a long type which function as the base of RANDOM
     */
    public MapParameterGenerator(long randomNumber) {
        RANDOM = new Random(randomNumber);
        WIDTH = 80;
        HEIGHT = 40;
        roomNumber = Math.abs(RANDOM.nextInt()) % 10 + 8;
        hallWayNumber = Math.abs(RANDOM.nextInt()) + roomNumber;
    }
}
