package evaluator.nodes;

import game.Player;

public class MoveCommandNode implements Node {
    protected String direction;
    Player player;
    public MoveCommandNode(String direction, Player player){
        this.direction = direction;
        this.player = player;
    }
    @Override
    public long evaluate() { //Todo implement evalutae
//        player.move(direction);
        throw new NotImplementYet();
//        return 0;
    }
}
