package evaluator.nodes;

import game.Player;

public class InfoExprNode implements Node{ // opponent | nearby
    private String info;
    private String direction;
    private Player player;

    public InfoExprNode(String info, String direction,Player player){ //nearby opponent
        this.info = info;
        this.direction = direction;
        this.player = player;
    }
    @Override
    public long evaluate() {
        player.infoMode(info, direction);
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
