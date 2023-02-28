package evaluator.nodes;

import game.Player;

public class InfoExprNode implements Node{ // opponent | nearby
    protected String info;
    protected String direction;
    protected Player player;

    public InfoExprNode(String info, String direction,Player player){
        this.info = info;
        this.direction = direction;
        this.player = player;
    }
    @Override
    public long evaluate() { //Todo implement info expression node
//        player.checkRegion(info, direction, player);
        return 0;
    }

    @Override
    public void print(StringBuilder s) {
        s.append(info);
        s.append(" "+ direction);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        print(builder);
        return builder.toString();
    }

}
