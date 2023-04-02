package UPBEAT.WebSocket;

import lombok.Getter;

@Getter
public class GameMessage {
    private PlayerMessage Player1;
    private PlayerMessage Player2;
    private TerritoryMessage territory;
    GameMessage(PlayerMessage p1, PlayerMessage p2, TerritoryMessage t){
        this.Player1 = p1;
        this.Player2 = p2;
        this.territory = t;
    }
}
