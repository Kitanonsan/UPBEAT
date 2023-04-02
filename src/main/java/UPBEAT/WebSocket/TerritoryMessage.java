package UPBEAT.WebSocket;
import game.Territory;
import lombok.Getter;

@Getter
public class TerritoryMessage {
    private RegionMessage[][] regionMessages;
    public TerritoryMessage(Territory territory){
        regionMessages = new RegionMessage[territory.getM()][territory.getN()];
        for(int i = 0 ; i < territory.getM() ; i++){
            for(int j = 0 ; j < territory.getN() ; j++){
                regionMessages[i][j] = new RegionMessage(territory.region(new int[]{i,j}));
            }
        }

    }
}
