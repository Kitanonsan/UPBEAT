package evaluator.nodes;

public class IfStatementNode implements Node {
    private Node ExprNode;
    private Node trueStatement, falseStatement;

    public IfStatementNode(Node ExprNode, Node trueStatement, Node falseStatement){
        this.ExprNode = ExprNode;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }
    @Override
    public long evaluate() {
        if (ExprNode.evaluate() != 0){
            trueStatement.evaluate();
        }else{
            falseStatement.evaluate();
        }
        return 0;
    }

    @Override
    public void print(StringBuilder s) {
        s.append("if ");
        ExprNode.print(s);
        s.append(" then ");
        trueStatement.print(s);
        s.append(" else ");
        falseStatement.print(s);

    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        print(builder);
        return builder.toString();
    }

}
