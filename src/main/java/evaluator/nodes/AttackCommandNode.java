package evaluator.nodes;

import game.Player;

public class AttackCommandNode implements Node{ //shoot
    protected String direction;
    protected Player player;
    public AttackCommandNode(String direction, Player player){
        this.direction = direction;
        this.player = player;
    }
    @Override
    public long evaluate() { //Todo implement evaluate Shoot command
        throw new NotImplementYet();
//        return 0;
    }
}
