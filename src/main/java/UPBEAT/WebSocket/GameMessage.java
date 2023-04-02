package UPBEAT.WebSocket;
import game.Territory;
import game.Player;
import lombok.Getter;

@Getter
public class GameMessage {
    private PlayerMessage[] Player;
    private RegionMessage[][] Territory;
    GameMessage(Player player1, Player player2,Territory territory){
        this.Player = new PlayerMessage[2];
        this.Territory = new RegionMessage[territory.getM()][territory.getN()];
        Player[0] = new PlayerMessage(player1);
        Player[1] = new PlayerMessage(player2);
        for(int i = 0 ; i < territory.getM() ; i++){
            for(int j = 0 ; j < territory.getN() ; j++){
                Territory[i][j] = new RegionMessage(territory.region(new int[]{i,j}));
            }
        }

    }
}
