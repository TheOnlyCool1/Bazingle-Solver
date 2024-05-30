import java.util.*;

public class WordMap {
    Set<String> allWords;
    Set<String>[] wordsWithLetter;
    Set<String>[][] letterPosWords;

    public WordMap() {
        allWords = new HashSet<>();
        allWords.addAll(Words.getWords());
        letterPosWords = (Set<String>[][]) (new HashSet<?>[26][7]);
        wordsWithLetter = (Set<String>[]) (new HashSet<?>[26]);
        for (String word : Words.getWords()) {
            for (int i = 0; i < word.length(); i++) {
                putWord(word, word.charAt(i), i);
            }
        }
    }

    void putWord(String word, char letter, int index) {
        if (letterPosWords[alphabetIndex(letter)][index] == null) {
            letterPosWords[alphabetIndex(letter)][index] = new HashSet<>();
        }
        letterPosWords[alphabetIndex(letter)][index].add(word);
        if (wordsWithLetter[alphabetIndex(letter)] == null) {
            wordsWithLetter[alphabetIndex(letter)] = new HashSet<>();
        }
        wordsWithLetter[alphabetIndex(letter)].add(word);
    }

    public Set<String> getWordsByLetterPos(char letter, int pos) {
        if (!Character.isLetter(letter))
            throw new IllegalArgumentException("Not valid character");
        return letterPosWords[alphabetIndex(letter)][pos];
    }

    public Set<String> getWordsWithLetter(char letter) {
        if (!Character.isLetter(letter))
            throw new IllegalArgumentException("Not valid character");
        return wordsWithLetter[alphabetIndex(letter)];
    }

    public int alphabetIndex(char l) {
        return l - 97;
    }

}
