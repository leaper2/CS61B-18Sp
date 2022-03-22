public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> dlld = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dlld.addLast(Character.valueOf(word.charAt(i)));
        }
        return dlld;
    }

    public boolean isPalindrome(String word) {
        Palindrome pldr = new Palindrome();
        Deque deqC1 = pldr.wordToDeque(word);
        Deque deqC2 = pldr.wordToDeque(word);
        boolean bool = true;
        while (deqC1.size() != 0) {
            bool = deqC1.removeFirst().equals(deqC2.removeLast());
            if (bool == false) {
                break;
            }
        }
        return bool;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Palindrome pldr = new Palindrome();
        Deque deqC1 = pldr.wordToDeque(word);
        // Deque deqC2 = pldr.wordToDeque(word);
        boolean bool = true;
        while (deqC1.size() > 1) {
            bool = cc.equalChars((char) deqC1.removeFirst(), (char) deqC1.removeLast());
            if (bool == false) {
                break;
            }
        }
        return bool;
    }
}
