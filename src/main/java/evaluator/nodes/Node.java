package evaluator.nodes;

public interface Node {
    long evaluate();
    void print(StringBuilder s);
    String toString();
}
