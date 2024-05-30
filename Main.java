import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        WordMap map = new WordMap();
        List<Board> result = new Board(map).recurse();
        System.out.println("Done!");
    }
}
