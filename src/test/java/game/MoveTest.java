package game;
import java.util.Random;
import game.Configuration;
import game.Player;
import game.Territory;
import org.junit.jupiter.api.Test;

public class MoveTest {
    Territory territory = new Territory();
    Player player = new Player("Player1",territory);
    Player player2 = new Player("Player2",territory);

    public void delay(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            return;
        }
    }

    @Test
    void MoveUpTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i ++){
            delay();
            player.move("up");
        }
    }

    @Test
    void MoveDownTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i ++){
            delay();
            player.move("down");
        }
    }

    @Test
    void MoveUprightTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i++){
            delay();
            player.move("upright");
        }
    }

    @Test
    void MoveUpLeftTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i++){
            delay();
            player.move("upleft");
        }
    }

    @Test
    void MoveDownLeftTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i++){
            delay();
            player.move("downleft");
        }
    }
    @Test
    void MoveDownRightTest(){
        player.printPosition();
        for(int i = 0 ; i < 20 ; i++){
            delay();
            player.move("downright");
        }
    }

    @Test
    void RandomMove(){
        player.printPosition();
        for(int i = 0 ; i < 30 ; i++){
            delay();
            player.randomMove();
        }
    }

    @Test
    void MoveUpButPlayerDone(){ //can't move after player done
        player.printPosition();
        player.done();
        for(int i = 0 ; i < 30 ; i++){
            player.move("up");
        }
    }

    @Test
    void MoveDownButPlayerDone(){ //can't move after player done
        player.printPosition();
        player.done();
        for(int i = 0 ; i < 30 ; i++){
            player.move("down");
        }
    }

    @Test
    void MoveUpRightButPlayerDone(){ //can't move after player done
        player.printPosition();
        player.done();
        for(int i = 0 ; i < 30 ; i++){
            player.move("upright");
        }
    }

    @Test
    void MoveUpLefttButPlayerDone(){ //can't move after player done
        player.printPosition();
        player.done();
        for(int i = 0 ; i < 30 ; i++){
            player.move("upleft");
        }
    }

    @Test
    void MoveDownRightButPlayerDone(){ //can't move after player done
        player.printPosition();
        player.done();
        for(int i = 0 ; i < 30 ; i++){
            player.move("downright");
        }
    }

    @Test
    void MoveDownLeftButPlayerDone(){ //can't move after player done
        player.printPosition();
        player.done();
        for(int i = 0 ; i < 30 ; i++){
            player.move("downleft");
        }
    }



}
