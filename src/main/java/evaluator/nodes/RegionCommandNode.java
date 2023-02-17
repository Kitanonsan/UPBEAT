package evaluator.nodes;

import game.Player;

public class RegionCommandNode implements Node{ // invest, collect
    protected String financeMode;
    protected Player player;

    public RegionCommandNode(String financeMode, Player player){
        this.financeMode = financeMode;
        this.player = player;
    }
    @Override
    public long evaluate() { //todo implement regioncommand
        throw new NotImplementYet();
//        return 0;
    }
}
