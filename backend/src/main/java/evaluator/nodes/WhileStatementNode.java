package evaluator.nodes;

public class WhileStatementNode implements Node {
    protected Node ExprNode;
    protected Node StatementNode;
    public WhileStatementNode(Node ExprNode, Node StatementNode){
        this.ExprNode = ExprNode;
        this.StatementNode = StatementNode;
    }
    @Override
    public long evaluate() {
        for (int counter = 0; counter < 10000 && ExprNode.evaluate() > 0; counter++){
            StatementNode.evaluate();
        }
        return 0;
    }
}
