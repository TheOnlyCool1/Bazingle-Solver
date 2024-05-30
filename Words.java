import java.util.*;
import java.io.*;

public class Words {
    private static List<String> list;

    public static List<String> getWords() {
        if (list == null) {
            File wordFile = new File("validGuesses.txt");
            Scanner scanner = null;
            try {
                scanner = new Scanner(wordFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            List<String> words = new ArrayList<>();
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
            list = words;
            scanner.close();
        }
        return list;
    }
}