package evaluator.nodes;

public class MoveCommandNode implements Node {
    protected String direction;
    public MoveCommandNode(String direction){
        this.direction = direction;
    }
    @Override
    public long evaluate() { //Todo implement evalutae
//        player.move(direction);
        throw new NotImplementYet();
//        return 0;
    }
}
