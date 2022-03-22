import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {

    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    /*
     * @Test
     * public void testWordToDeque() {
     * Deque d = palindrome.wordToDeque("persiflage");
     * String actual = "";
     * for (int i = 0; i < "persiflage".length(); i++) {
     * actual += d.removeFirst();
     * }
     * assertEquals("persiflage", actual);
     * }
     * /*
     * Uncomment this
     * class once you've created your Palindrome class.
     */
    @Test
    public void testIsPalindrome() {
        String a = "a";
        String b = "racecar";
        String c = "horse";
        String A = "A";
        String Aa = "Aa";
        assertTrue(a + " is a palindrome", palindrome.isPalindrome(a));
        assertTrue(b + " is a palindrome", palindrome.isPalindrome(b));
        assertTrue(A + " is a palindrome", palindrome.isPalindrome(A));
        assertTrue("0 length string is also a palindrome", palindrome.isPalindrome(""));
        assertFalse(c + " is not a palindrome", palindrome.isPalindrome(c));
        assertFalse(Aa + " is not a palindrome", palindrome.isPalindrome(Aa));

        String[] pldrSArray = new String[] { "1o1", "aaaaa", "BaB", "", " ", "  " };
        for (String s : pldrSArray) {
            assertTrue(s + " is a palindrome", palindrome.isPalindrome(s));
        }
        String[] nonPldrSArray = new String[] {};
    }

    @Test
    public void testIsPalindromeOBO() {
        OffByOne obo = new OffByOne();
        String[] pldrSArray = new String[] { "flake", "ab", "acb", "1", "" };
        for (String s : pldrSArray) {
            assertTrue(s + " is a palindrome", palindrome.isPalindrome(s, obo));
        }
    }
}
