package evaluator.nodes;

import game.Player;

public class RegionCommandNode implements Node{ // invest, collect
    protected String financeMode;
    protected Player player;
    protected Node Expression;

    public RegionCommandNode(String financeMode,Node Expression, Player player){
        this.financeMode = financeMode;
        this.Expression = Expression;
        this.player = player;
    }
    @Override
    public long evaluate() { //todo implement RegionCommand
        throw new NotImplementYet();
//        return 0;
    }

    @Override
    public void print(StringBuilder s) {
        s.append(financeMode);
        s.append(" " + Expression.evaluate());
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        print(builder);
        return builder.toString();
    }

}
