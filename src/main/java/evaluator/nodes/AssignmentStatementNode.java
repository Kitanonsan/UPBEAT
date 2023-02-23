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
        variable.assignValue(ExprNode.evaluate());
        return 0;
    }

    @Override
    public void print() {
        System.out.println("Assign :" + variable);
    }


}
