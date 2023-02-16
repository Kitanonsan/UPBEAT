package evaluator.nodes;

import java.util.ArrayList;
import java.util.List;

public class BlockStatementNode implements Node {
    protected List<Node> statements;
    public BlockStatementNode(){
        statements = new ArrayList<>();
    }

    public void add(Node statementNode){
        statements.add(statementNode);
    }
    @Override
    public long evaluate() {
        for (Node statement : statements){
            statement.evaluate();
        }
        return 0;
    }
}
