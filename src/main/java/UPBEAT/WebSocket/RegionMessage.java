package UPBEAT.WebSocket;

import game.Region;
import lombok.Getter;
@Getter
public class RegionMessage {
    private int deposit;
    private String owner;
    private boolean isCenterCity;

    RegionMessage(Region r){
        deposit = r.getDeposit();
        if(r.getOwner() == null)
            owner = "";
        else
            owner = r.getOwner().getName();
        isCenterCity = r.isCenterCity();
    }
}
