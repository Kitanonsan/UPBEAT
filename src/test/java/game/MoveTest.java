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
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move("up");
        }
    }

    @Test
    void MoveDownTest(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move("down");
        }
    }

    @Test
    void MoveUprightTest(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move("upright");
        }
    }

    @Test
    void MoveUpLeftTest(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move("upleft");
        }
    }

    @Test
    void MoveDownLeftTest(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move("downleft");
        }
    }
    @Test
    void MoveDownRightTest(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move("downright");
        }
    }

    @Test
    void RandomMove(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.randomMove();
        }
    }

    @Test
    void MoveUpButPlayerDone(){ //can't move after player done
        player.printInfo();
        player.done();
        player.move("up");
    }

    @Test
    void MoveDownButPlayerDone(){ //can't move after player done
        player.printInfo();
        player.done();
        player.move("down");
    }

    @Test
    void MoveUpRightButPlayerDone(){ //can't move after player done
        player.printInfo();
        player.done();
        player.move("upright");
    }

    @Test
    void MoveUpLefttButPlayerDone(){ //can't move after player done
        player.printInfo();
        player.done();
        player.move("upleft");
    }

    @Test
    void MoveDownRightButPlayerDone(){ //can't move after player done
        player.printInfo();
        player.done();
        player.move("downright");
    }

    @Test
    void MoveDownLeftButPlayerDone(){ //can't move after player done
        player.printInfo();
        player.done();
        player.move("downleft");
    }
    @Test
    void NotEnoughBudgetToMove(){
        Player player = new Player("p",territory,8,8,0);
        player.printInfo();
        player.move("up");
    }

}
