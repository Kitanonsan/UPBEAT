package game;

import evaluator.GrammarParser;
import evaluator.Parser;
import evaluator.SyntaxError;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class Game {

    Player currentPlayer;
    int index;
    Territory territory;
    Player[] players;
    int[] turn;

    @MessageMapping("/start")
    public void start(){
        this.territory = new Territory();
        players[0] = new Player("Player1",this.territory);
        players[1] = new Player("Player2",this.territory);
        turn[0] = 0;
        turn[1] =0;
        index = 0;
        currentPlayer = players[index];
    }

    @MessageMapping("/territory")
    @SendTo("/topic/territory")
    public Region[][] territory(){
        return this.territory.regions();
    }

    @MessageMapping("/newTurn")
    public void newTurn(){
        index = (index + 1)%2;
        currentPlayer = players[index];
        turn[index]++;
        currentPlayer.updateRegions(turn[index]);
    }



}
