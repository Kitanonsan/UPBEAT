package UPBEAT.WebSocket;
import game.Territory;
import game.Player;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class GameMessage {
    private PlayerMessage[] Player;
    private RegionMessage[] Territory;
    GameMessage(Player player1, Player player2,Territory territory){
        this.Player = new PlayerMessage[2];
        this.Territory = new RegionMessage[territory.getM()+ territory.getN()];
        Player[0] = new PlayerMessage(player1);
        Player[1] = new PlayerMessage(player2);
        ArrayList<RegionMessage> arrayList = new ArrayList<>();
        for(int i = 0 ; i < territory.getM() ; i++){
            for(int j = 0 ; j < territory.getN() ; j++){
                arrayList.add(new RegionMessage(territory.region(new int[]{i,j})));
            }
        }
        Territory = arrayList.toArray(Territory);
    }
}
