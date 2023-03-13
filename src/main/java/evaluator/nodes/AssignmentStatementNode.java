package evaluator.nodes;

public class AssignmentStatementNode implements Node {
    private IdentifierNode variable;
    private Node ExprNode;

    public AssignmentStatementNode(IdentifierNode variable, Node ExprNode){
        this.variable = variable;
        this.ExprNode = ExprNode;
    }
    @Override
    public long evaluate() {
        variable.assignValue(ExprNode.evaluate());
        return 0;
    }

    @Override
    public void print(StringBuilder s) {
        s.append(variable);
        s.append("=");
        ExprNode.print(new StringBuilder("" + ExprNode.evaluate()));
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        print(builder);
        return builder.toString();
    }
}
