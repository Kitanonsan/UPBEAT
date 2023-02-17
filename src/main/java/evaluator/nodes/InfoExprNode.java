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
    public long evaluate() {
//        player.checkRegion(info, direction, player);
        return 0;
    }
}
