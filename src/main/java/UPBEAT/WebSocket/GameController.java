package UPBEAT.WebSocket;
import game.Player;
import game.Territory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        if (playerBody.getName() == currentPlayer.getName()){
            if(currentPlayer.getName().equals("Player1")){
                System.out.println("Player 1 Edit Plan.");
                try{
                    FileWriter Write = new FileWriter("P1_Plan.txt");
                }catch (IOException e){
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else if (currentPlayer.getName().equals("Player2")) {
                System.out.println("Player 2 Edit Plan.");
                try{
                    FileWriter Write = new FileWriter("P2_Plan.txt");
                }catch (IOException e){
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

            }
        }
    }

    @PutMapping("/game/parse")
    @SendTo("/topic/territory")
    public  TerritoryMessage parsePlan(@RequestBody PlayerBody body){
        if(body.getName() == currentPlayer.getName()){
            if (currentPlayer.getName().equals("Player1")){
                Path file = Paths.get("./P1_plan.txt");
                Charset charset = Charset.forName("UTF-8");
                try (BufferedReader reader = Files.newBufferedReader(file, charset)){
                    String line = null;
                    while ((line = reader.readLine()) != null){
                        parsePlan(body);
                    }
                }catch (IOException e){
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else if (currentPlayer.getName().equals("Player2")){
                Path file = Paths.get("./P2_plan.txt");
                Charset charset = Charset.forName("UTF-8");
                try (BufferedReader reader = Files.newBufferedReader(file, charset)){
                    String line = null;
                    while ((line = reader.readLine()) != null){
                        parsePlan(body);
                    }
                }catch (IOException e){
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }
        return new TerritoryMessage(this.territory);
    }

    @SubscribeMapping("/territory")
    public TerritoryMessage sentTerritoryMessage(){
        return new TerritoryMessage(this.territory);
    }

    @GetMapping("/game/data")
    public GameMessage sendGameMessage(){
        return new GameMessage(new PlayerMessage(playerArray[0]),new PlayerMessage(playerArray[1]),new TerritoryMessage(territory));
    }
}
