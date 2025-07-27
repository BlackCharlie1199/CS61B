public class GuitarHero {
    private static final String keyBoard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int keyNumber = keyBoard.length();
    private static final synthesizer.GuitarString[] guitarStrings = new synthesizer.GuitarString[keyNumber];

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        for (int i = 0; i < keyNumber; i++) {
            guitarStrings[i] = new synthesizer.GuitarString(440.0 * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for(int i = 0; i < keyNumber; i++) {
                    if (key == keyBoard.charAt(i)) {
                        guitarStrings[i].pluck();
                        break;
                    }
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for(int i = 0; i < keyNumber; i++) {
                sample += guitarStrings[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(int i = 0; i < keyNumber; i++) {
                guitarStrings[i].tic();
            }
        }
    }

}
