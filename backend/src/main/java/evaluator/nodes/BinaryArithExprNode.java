package evaluator.nodes;

public class BinaryArithExprNode implements Node{
    protected Node left, right;
    protected String op;

    public BinaryArithExprNode(Node left, String op, Node right){
        this.left = left;
        this.op = op;
        this.right = right;

    }
    @Override
    public long evaluate() throws ArithmeticException {
        long lv = left.evaluate();
        return 0;
    }
}
