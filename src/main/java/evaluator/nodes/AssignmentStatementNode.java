package evaluator.nodes;

public class AssignmentStatementNode implements Node {
    protected IdentifierNode variable;
    protected Node ExprNode;

    public AssignmentStatementNode(IdentifierNode variable, Node ExprNode){
        this.variable = variable;
        this.ExprNode = ExprNode;
    }
    @Override
    public long evaluate() {
        variable.assign(ExprNode.evaluate());
        return 0;
    }
}
