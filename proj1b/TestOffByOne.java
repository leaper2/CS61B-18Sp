import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars() {
        char b = 'b';
        char c = 'c';
        char d = 'd';
        assertTrue(b + " is equal to " + c, offByOne.equalChars(b, c));
        assertTrue(d + " is equal to " + c, offByOne.equalChars(d, c));
        // assertTrue(b + " is equal to " + 0, offByOne.equalChars(b, (char) 0));
        assertTrue("&" + " is equal to " + "%", offByOne.equalChars('&', '%'));
        assertTrue("Y" + " is equal to " + "Z", offByOne.equalChars('Y', 'Z'));
        assertFalse(d + " is equal to " + b, offByOne.equalChars(d, b));
        assertFalse(d + " is equal to " + d, offByOne.equalChars(d, d));
        assertFalse(d + " is equal to " + "E", offByOne.equalChars(d, 'E'));
    }

    // Your tests go here.
    // Uncomment this class once you've created your CharacterComparator interface
    // and OffByOne class. **/
}
