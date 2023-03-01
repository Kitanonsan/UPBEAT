package game;
import java.util.Random;
import game.Configuration;
import game.Player;
import game.Territory;
import org.junit.jupiter.api.Test;

public class MoveTest {
    Configuration config = new Configuration();
    Territory territory = new Territory();
    Player player = new Player("Player1",territory);
    Player player2 = new Player("Player2",territory);

    public void delay(long millis){
        try {
            Thread.sleep(millis); // Sleep for 1 second
        } catch (InterruptedException e) {
            return;
        }
    }

    @Test
    void MoveUpTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i ++){
            delay(1000);
            player.move("up");
        }
    }

    @Test
    void MoveDownTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i ++){
            delay(1000);
            player.move("down");
        }
    }

    @Test
    void MoveUprightTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i++){
            delay(1000);
            player.move("upright");
        }
    }

    @Test
    void MoveUpLeftTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i++){
            delay(1000);
            player.move("upleft");
        }
    }

    @Test
    void MoveDownLeftTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i++){
            delay(1000);
            player.move("downleft");
        }
    }
    @Test
    void MoveDownRightTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i++){
            delay(1000);
            player.move("downright");
        }
    }

    @Test
    void RandomMove(){
        player.printPosition();
        for(int i = 0 ; i < 30 ; i++){
            delay(1000);
            player.randomMove();
        }
    }
}
