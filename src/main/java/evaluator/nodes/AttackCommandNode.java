package evaluator.nodes;

import game.Player;

public class AttackCommandNode implements Node{ //shoot
    protected String direction;
    protected Player player;
    Node expression;
    public AttackCommandNode(String direction, Player player, Node exp){
        this.direction = direction;
        this.player = player;
        this.expression = exp;

    }
    @Override
    public long evaluate() { //Todo implement evaluate Shoot command
        throw new NotImplementYet();
//        player.move(direction);
//        player.shoot(direction);
//        return 0;
    }

    @Override
    public void print(StringBuilder s) {
        s.append("shoot ");
        s.append(direction + " ");
        s.append(expression);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        print(builder);
        return builder.toString();
    }
}
