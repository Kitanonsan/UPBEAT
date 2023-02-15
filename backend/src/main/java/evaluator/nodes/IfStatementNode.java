package evaluator.nodes;

public class IfStatementNode implements Node {
    protected Node ExprNode;
    protected Node trueStatement, falseStatement;

    public IfStatementNode(Node ExprNode, Node trueStatement, Node falseStatement){
        this.ExprNode = ExprNode;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }
    @Override
    public long evaluate() {
        if (ExprNode.evaluate() == 1){
            trueStatement.evaluate();
        }else{
            falseStatement.evaluate();
        }
        return 0;
    }
}
