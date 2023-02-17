package evaluator.nodes;

import game.Player;

public class DoneNode implements Node{
    Player player;
    public DoneNode(Player player){
        this.player = player;
    }
    @Override
    public long evaluate() {
//        player.done();
        return 0;
    }
}
