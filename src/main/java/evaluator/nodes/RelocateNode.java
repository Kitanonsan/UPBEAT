package evaluator.nodes;

import game.Player;

public class RelocateNode implements Node{
    Player player;

    public RelocateNode(Player player){
        this.player = player;
    }
    @Override
    public long evaluate() { //Todo implement relocate
//        throw new NotImplementYet();
        return 0;
    }

    @Override
    public void print(StringBuilder s) {
        s.append("relocate");
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        print(builder);
        return builder.toString();
    }

}
