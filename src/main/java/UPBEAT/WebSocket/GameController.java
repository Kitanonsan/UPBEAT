package UPBEAT.WebSocket;
import game.Player;
import game.Territory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@Controller
public class GameController {

    Territory territory;
    Player[] playerArray;
    int[] turn;
    Player currentPlayer;
    int index;
    @PostMapping("/game/start")
    public void start(){
        System.out.println("Start Game!");
        this.territory = new Territory();
        Player p1 = new Player("Player1",territory);
        Player p2 = new Player("Player2",territory);
        this.playerArray = new Player[2];
        playerArray[0] = p1;
        playerArray[1] = p2;
        this.turn = new int[2];
        index = 1;
        currentPlayer = playerArray[index];
    }

    @PutMapping("/game/turn")
    public void newTurn(){
        index = (index+1)%2;
        turn[index]++;
        currentPlayer = playerArray[index];
        currentPlayer.updateRegions(turn[index]);
        System.out.println("CurrentPlayer: " + currentPlayer.getName() + " turnCount: " + turn[index]);
    }

    @PutMapping("/game/edit")
    public void editConstructionPlan(@RequestBody PlayerBody playerBody){

    }

    @PutMapping("/game/parse")
    @SendTo("/topic/territory")
    public  TerritoryMessage parsePlan(@RequestBody PlayerBody body){
        if(body.getName() == currentPlayer.getName()){

        }
        return new TerritoryMessage(this.territory);
    }

    @SubscribeMapping("/territory")
    public TerritoryMessage sentTerritoryMessage(){
        return new TerritoryMessage(this.territory);
    }


}
