import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }


    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("abba"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("aba"));
        assertTrue(palindrome.isPalindrome("abababa"));
        assertTrue(palindrome.isPalindrome("cc"));
    }

    @Test
    /* Test the overloaded isPalindrome function which receive two arguments, word and compare method. */
    public void testIsPalindrome2Args() {
        OffByOne offByone = new OffByOne();
        assertTrue(palindrome.isPalindrome("a", offByone));
        assertTrue(palindrome.isPalindrome("abab", offByone));
        assertFalse(palindrome.isPalindrome("cat", offByone));
        assertTrue(palindrome.isPalindrome("", offByone));
        assertTrue(palindrome.isPalindrome("abb", offByone));
        assertTrue(palindrome.isPalindrome("ababbab", offByone));
        assertFalse(palindrome.isPalindrome("cc", offByone));
    }
}
