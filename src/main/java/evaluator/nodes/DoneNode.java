package evaluator.nodes;

import game.Player;

public class DoneNode implements Node{
    Player player;
    public DoneNode(Player player){
        this.player = player;
    }
    @Override
    public long evaluate() {
        player.done();
        return 0;
    }

    @Override
    public void print(StringBuilder s) {
        s.append("done");
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        print(builder);
        return builder.toString();
    }


}
