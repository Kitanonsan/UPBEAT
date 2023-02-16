package evaluator.nodes;

public class AttackCommandNode implements Node{ //shoot
    protected String direction;
    protected Node ExprNode;
    public AttackCommandNode(String direction, Node ExprNode){
        this.direction = direction;
        this.ExprNode = ExprNode;
    }
    @Override
    public long evaluate() { //Todo implement evaluate Shoot command
        throw new NotImplementYet();
//        return 0;
    }
}
