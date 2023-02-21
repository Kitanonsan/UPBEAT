package evaluator.nodes;

import game.Player;

public class RelocateNode implements Node{
    protected String direction;
    Player player;

    public RelocateNode(String direction, Player player){
        this.direction = direction;
        this.player = player;
    }
    @Override
    public long evaluate() {
        return 0;
    }

    @Override
    public void print() {

    }
}
