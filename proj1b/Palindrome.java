public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeRecursively(wordDeque);
    }

    private boolean isPalindromeRecursively(Deque<Character> word) {
        if (word.size() == 1 || word.isEmpty()) {
            return true;
        }
        return word.removeFirst() == word.removeLast() && isPalindromeRecursively(word);
    }

    private boolean isPalindromeIteratively(String word) {
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length()-i-1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        for (int i = 0; i < word.length() / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length()-i-1))) {
                return false;
            }
        }
        return true;
    }
}