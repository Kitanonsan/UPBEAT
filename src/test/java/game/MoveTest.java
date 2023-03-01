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

    @Test
    void MoveUpTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i ++){
            player.move("up");
        }
    }

    @Test
    void MoveDownTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i ++){
            player.move("down");
        }
    }

    @Test
    void MoveUprightTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i++){
            player.move("upright");
        }
    }
}
