package UPBEAT.WebSocket;
import game.Configuration;
import game.Player;
import game.Territory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@Controller
public class GameController {
    Territory territory;
    Player[] players;
    int[] turn;
    Player currentPlayer;
    int index;
    @PostMapping("/game/start")
    public void start(){
        System.out.println("Start Game!");
        this.territory = new Territory();
        Player p1 = new Player("Player1",territory);
        Player p2 = new Player("Player2",territory);
        this.players = new Player[2];
        players[0] = p1;
        players[1] = p2;
        this.turn = new int[2];
        index = 1;
        currentPlayer = players[index];
    }

    @PostMapping("/game/turn")
    @SendTo("/topic/player")
    public PlayerBody newTurn(){
        index = (index+1)%2;
        turn[index]++;
        currentPlayer = players[index];
        currentPlayer.updateRegions(turn[index]);
        System.out.println("CurrentPlayer: " + currentPlayer.getName() + " turnCount: " + turn[index]);
        return new PlayerBody(currentPlayer.getName(),"");
    }

    @PostMapping("/game/set")
    public void setPlan(@RequestBody PlayerBody playerBody){
        System.out.println(playerBody.getName() + " set plan.");
        if (playerBody.getName().equals("Player1")){
            try(FileWriter writer = new FileWriter("src/Construction_Plan/P1_Plan.txt");
                BufferedWriter bw = new BufferedWriter(writer)){
                bw.write(playerBody.getPlan());
                bw.close();
                System.out.println("Finish");
            }catch (IOException e){
                System.out.println("An error occurred");
                e.printStackTrace();
            }
        } else if (playerBody.getName().equals("Player2")) {
            try(FileWriter writer = new FileWriter("src/Construction_Plan/P2_Plan.txt");
                BufferedWriter bw = new BufferedWriter(writer)){
                bw.write(playerBody.getPlan());
                bw.close();
            }catch (IOException e){
                System.out.println("An error occurred");
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/game/edit")
    public void editPlan(@RequestBody PlayerBody playerBody){ //Write file
        if (playerBody.getName() == currentPlayer.getName()){
            if(currentPlayer.pay(Configuration.instance().getRev_cost())){
                if (playerBody.getName().equals("Player1")){
                    try(FileWriter writer = new FileWriter("src/Construction_Plan/P1_Plan.txt");
                        BufferedWriter bw = new BufferedWriter(writer)){
                        bw.write(playerBody.getPlan());
                        bw.close();
                    }catch (IOException e){
                        System.out.println("An error occurred");
                        e.printStackTrace();
                    }
                }else if (playerBody.getName().equals("Player2")){
                    try (FileWriter writer = new FileWriter("src/Construction_Plan/P2_Plan.txt");
                         BufferedWriter bw = new BufferedWriter(writer)){
                        bw.write(playerBody.getPlan());
                        bw.close();
                    }catch (IOException e){
                        System.out.println("An error occurred");
                        e.printStackTrace();
                    }
                }
                System.out.println(currentPlayer.getName() + " edit construction plan.");
            }
            else{
                System.out.println(currentPlayer.getName() + ": not enough budget to edit construction plan. ");
            }
        }
    }
    @PostMapping("/game/parse")
    @SendTo("/topic/message")
    public  GameMessage parsePlan(@RequestBody PlayerBody body){
        if(body.getName() == currentPlayer.getName()){
            if(currentPlayer.pay(Configuration.instance().getRev_cost())){
                if (currentPlayer.getName().equals("Player1")){
                    Path file = Paths.get("src/Construction_Plan/P1_plan.txt");
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
                    Path file = Paths.get("src/Construction_Plan/P2_plan.txt");
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
        }
        return new GameMessage(players[0], players[1],territory);
    }
    @GetMapping("/game/message")
    public GameMessage getGameMessage(){
        return new GameMessage(players[0], players[1],territory);
    }
    @SubscribeMapping("/message")
    public GameMessage sendGameMessage(){
        return new GameMessage(players[0], players[1],territory);
    }
    @GetMapping("/game/player")
    public PlayerBody getCurrentPlayer(){
        return new PlayerBody(currentPlayer.getName(),"");
    }
    @SubscribeMapping("/player")
    public PlayerBody sendCurrentPlayer(){
        return new PlayerBody(currentPlayer.getName(),"");
    }

}
