package evaluator;

import evaluator.nodes.Node;

public interface Parser {
    Node parse() throws SyntaxError;
}
