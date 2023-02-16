package evaluator.nodes;

public class NumberNode implements Node{
    protected Long number;

    public NumberNode(Long number){
        this.number = number;
    }
    @Override
    public long evaluate() {
        return number;
    }
}
