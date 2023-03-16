package game;
import java.util.Random;
import game.Configuration;
import game.Player;
import game.Territory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {
    @Test
    void MoveUpTest(){
        Territory territory = new Territory();
        Player player = new Player("player",territory,9,0,1000);
        player.printInfo();
        for(int i = 0 ; i < 7; i++){
            int[] beforeMovePosition = player.getPosition();
            player.move("up");
            assertEquals(beforeMovePosition[0]-1,player.getPosition()[0]);
            assertEquals(beforeMovePosition[1],player.getPosition()[1]);
        }
    }

    @Test
    void MoveDownTest(){
        Territory territory = new Territory();
        Player player = new Player("player",territory,0,0,1000);
        player.printInfo();
        for(int i = 0 ; i < 7 ; i++){
            int[] beforeMovePosition = player.getPosition();
            player.move("down");
            assertEquals(beforeMovePosition[0]+1,player.getPosition()[0]);
            assertEquals(beforeMovePosition[1],player.getPosition()[1]);
        }
    }

    @Test
    void MoveUprightTest(){
        Territory territory = new Territory();
        Player player = new Player("player",territory,9,0,1000);
        player.printInfo();
        for(int i = 0 ; i < 7; i++){
            int[] beforeMovePosition = player.getPosition();
            player.move("upright");
            assertEquals(beforeMovePosition[1]%2==0?beforeMovePosition[0]:beforeMovePosition[0]-1,player.getPosition()[0]);
            assertEquals(beforeMovePosition[1]+1,player.getPosition()[1]);
        }
    }

    @Test
    void MoveUpLeftTest(){
        Territory territory = new Territory();
        Player player = new Player("player",territory,9,14,1000);
        player.printInfo();
        for(int i = 0 ; i < 7 ; i++){
            int[] beforeMovePosition = player.getPosition();
            player.move("upleft");
            assertEquals(beforeMovePosition[1]%2==0?beforeMovePosition[0]:beforeMovePosition[0]-1,player.getPosition()[0]);
            assertEquals(beforeMovePosition[1]-1,player.getPosition()[1]);
        }
    }

    @Test
    void MoveDownLeftTest(){
        Territory territory = new Territory();
        Player player = new Player("player",territory,0,14,1000);
        player.printInfo();
        for(int i = 0 ; i < 7 ; i++){
            int[] beforeMovePosition = player.getPosition();
            player.move("downleft");
            assertEquals(beforeMovePosition[1]%2==0?beforeMovePosition[0]+1:beforeMovePosition[0],player.getPosition()[0]);
            assertEquals(beforeMovePosition[1]-1,player.getPosition()[1]);
        }
    }
    @Test
    void MoveDownRightTest(){
        Territory territory = new Territory();
        Player player = new Player("player",territory,0,0,1000);
        player.printInfo();
        for(int i = 0 ; i < 7 ; i++){
            int[] beforeMovePosition = player.getPosition();
            player.move("downright");
            assertEquals(beforeMovePosition[1]%2==0?beforeMovePosition[0]+1:beforeMovePosition[0],player.getPosition()[0]);
            assertEquals(beforeMovePosition[1]+1,player.getPosition()[1]);
        }
    }

    @Test
    void RandomMove(){
        Territory territory = new Territory();
        Player player = new Player("player",territory,0,0,1000);
        player.printInfo();;
        for(int i = 0 ; i < 20 ; i++){
            player.randomMove();
        }
    }

    @Test
    void MoveUpButPlayerDone(){ //can't move after player done
        Territory territory = new Territory();
        Player player = new Player("player",territory,7,7,1000);
        player.printInfo();
        player.done();
        int[] beforeMovePostion = player.getPosition();
        player.move("up");
        assertEquals(beforeMovePostion[0],player.getPosition()[0]);
        assertEquals(beforeMovePostion[1],player.getPosition()[1]);
    }

    @Test
    void MoveDownButPlayerDone(){ //can't move after player done
        Territory territory = new Territory();
        Player player = new Player("player",territory,7,7,1000);
        player.printInfo();
        player.done();
        int[] beforeMovePostion = player.getPosition();
        player.move("down");
        assertEquals(beforeMovePostion[0],player.getPosition()[0]);
        assertEquals(beforeMovePostion[1],player.getPosition()[1]);
    }

    @Test
    void MoveUpRightButPlayerDone(){ //can't move after player done
        Territory territory = new Territory();
        Player player = new Player("player",territory,7,7,1000);
        player.printInfo();
        player.done();
        int[] beforeMovePostion = player.getPosition();
        player.move("upright");
        assertEquals(beforeMovePostion[0],player.getPosition()[0]);
        assertEquals(beforeMovePostion[1],player.getPosition()[1]);
    }

    @Test
    void MoveUpLefttButPlayerDone(){ //can't move after player done
        Territory territory = new Territory();
        Player player = new Player("player",territory,7,7,1000);
        player.printInfo();
        player.done();
        int[] beforeMovePostion = player.getPosition();
        player.move("upleft");
        assertEquals(beforeMovePostion[0],player.getPosition()[0]);
        assertEquals(beforeMovePostion[1],player.getPosition()[1]);
    }

    @Test
    void MoveDownRightButPlayerDone(){ //can't move after player done
        Territory territory = new Territory();
        Player player = new Player("player",territory,7,7,1000);
        player.printInfo();
        player.done();
        int[] beforeMovePostion = player.getPosition();
        player.move("downright");
        assertEquals(beforeMovePostion[0],player.getPosition()[0]);
        assertEquals(beforeMovePostion[1],player.getPosition()[1]);
    }
    @Test
    void MoveDownLeftButPlayerDone(){ //can't move after player done
        Territory territory = new Territory();
        Player player = new Player("player",territory,7,7,1000);
        player.printInfo();
        player.done();
        int[] beforeMovePostion = player.getPosition();
        player.move("downleft");
        assertEquals(beforeMovePostion[0],player.getPosition()[0]);
        assertEquals(beforeMovePostion[1],player.getPosition()[1]);
    }
    @Test
    void NotEnoughBudgetToMove(){
        Territory territory = new Territory();
        Player player = new Player("p",territory,8,8,0);
        player.printInfo();
        int[] beforeMovePostion = player.getPosition();
        player.move("up");
        assertEquals(beforeMovePostion[0],player.getPosition()[0]);
        assertEquals(beforeMovePostion[1],player.getPosition()[1]);
    }

}
