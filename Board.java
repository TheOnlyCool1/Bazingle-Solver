import java.util.*;

public class Board {

    // Who'd've guessed, honestly
    static String solution = "bazinga";

    WordMap wordMap;
    List<String> guesses;
    Set<String> availableWords;

    public Board(WordMap wm) {
        wordMap = wm;
        guesses = new ArrayList<>();
        availableWords = wm.allWords;
    }

    public Board(Board b, String word) {
        wordMap = b.wordMap;
        guesses = new ArrayList<>(b.guesses);
        availableWords = new HashSet<>(b.availableWords);
        applyWord(word);
    }

    void applyWord(String word) {
        guesses.add(word);
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // Green Hints
            if (c == solution.charAt(i)) {
                availableWords.retainAll(wordMap.getWordsByLetterPos(c, i));
            }
            // Yellow Hints
            if (containsLetter(c)) {
                availableWords.retainAll(wordMap.getWordsWithLetter(c));
                // Since yellow hints mean the letter's NOT there.
                availableWords.removeAll(wordMap.getWordsByLetterPos(c, i));
            } else {
                availableWords.removeAll(wordMap.getWordsWithLetter(c));
            }
        }
    }

    public List<Board> recurse() {
        List<Board> result = new ArrayList<>();
        for (String word : availableWords) {
            if (word.equals(solution)) {
                Board b = new Board(this, solution);
                if (b.guesses.size() > 6) {
                    result.add(b);
                    System.out.println(b.guesses);
                }
            } else {
                result.addAll(new Board(this, word).recurse());
            }
        }
        return result;
    }

    boolean containsLetter(char c) {
        // Hard-coded because whatever tbh
        return switch (c) {
            case 'b', 'a', 'z', 'i', 'n', 'g' -> true;
            default -> false;
        };
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(guesses.size());
        for (String w : guesses) {
            result.append(w);
            result.append("\n");
        }
        return result.toString();
    }

    //    public Set<String> possibleWordsFromState() {
//        Set<String> result = null;
//        for (Hint g : greenHints) {
//            if (result == null)
//                result =
//        }
//        if (result == null) {
//            return new HashSet<String>();
//        }
//        return result;
//    }

}