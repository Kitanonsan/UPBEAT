package evaluator.nodes;

import java.util.ArrayList;
import java.util.List;

public class BlockStatementNode implements Node {
    protected List<Node> statements;
    public BlockStatementNode(){
        statements = new ArrayList<>();
    }

    public void addStatement(Node statementNode){
        statements.add(statementNode);
    }
    @Override
    public long evaluate() {
        for (Node statement : statements){
            statement.evaluate();
        }
        return 0;
    }

    @Override
    public void print(StringBuilder s) {
        s.append("{");
        for (Node statement : statements){
            statement.print(s);
        }
        s.append("}");
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        print(builder);
        return builder.toString();
    }
}
