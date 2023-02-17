package evaluator.nodes;

import game.Player;

public class InfoExprNode implements Node{ // opponent | nearby
    protected String info;
    protected Player player;

    public InfoExprNode(String info, Player player){
        this.info = info;
        this.player = player;
    }
    @Override
    public long evaluate() {
        return 0;
    }
}
