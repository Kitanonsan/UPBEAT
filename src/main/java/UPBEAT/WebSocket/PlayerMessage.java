package UPBEAT.WebSocket;

import game.Player;
import lombok.Getter;

@Getter
public class PlayerMessage {
    private String name;
    private long budget;
    private int[] position;
    private int[] city_position;
    private boolean isPlayerDone;
    private boolean isPlayerLost;

    public PlayerMessage(Player p){
        this.name = p.getName();
        this.budget = p.getBudget();
        position = p.getPosition();
        city_position = p.getCityPosition();
        isPlayerLost = p.lose();
        isPlayerDone = p.isPlayerDone();
    }
}
