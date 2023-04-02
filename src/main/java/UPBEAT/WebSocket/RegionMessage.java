package UPBEAT.WebSocket;

import game.Region;
import lombok.Getter;
@Getter
public class RegionMessage {
    private int[] position;
    private int deposit;
    private double interestRate;
    private String owner;
    private boolean isCenterCity;

    RegionMessage(Region r){
        position = r.getPosition();;
        deposit = r.getDeposit();
        interestRate = (double) r.getInterestRate();
        if(r.getOwner() == null)
            owner = "";
        else
            owner = r.getOwner().getName();
        isCenterCity = r.isCenterCity();
    }
}
