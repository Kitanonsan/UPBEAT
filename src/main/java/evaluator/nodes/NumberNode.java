package evaluator.nodes;

public class NumberNode implements Node{
    protected int number;

    public NumberNode(int number){
        this.number = number;
    }
    @Override
    public long evaluate() {
        return number;
    }

    @Override
    public void print() {

    }
}
