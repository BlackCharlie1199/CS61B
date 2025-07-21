public class OffByN implements CharacterComparator {
    private int diff;

    /* Make a constructor return the object whose equalChars return true for N diffs. */
    public OffByN(int N) {
        diff = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x-y) == diff;
    }
}
