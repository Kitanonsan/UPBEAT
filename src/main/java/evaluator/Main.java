package evaluator;

import evaluator.nodes.Node;
import game.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException, SyntaxError {
        Path file = Path.of("./input.txt");
        String str = Files.readString(file);
        Parser parser = new Parser(str);
        Node node = parser.parsePlan();
        return;
    }
}
