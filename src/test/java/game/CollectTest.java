package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CollectTest {
    @Test
    void collectRegion(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,5,5,1000);
        p1.printInfo();
        p1.move("upright");
        p1.invest(200);
        p1.printInfo();
        long beforeCollectBudget = p1.Budget();
        p1.collect(100);
        p1.printInfo();
        assertEquals(p1.Budget(),beforeCollectBudget-1+100); //for execute command -1
    }
    @Test
    void collectOpponentRegion(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,5,5,1000);
        Player p2 = new Player("P2",territory,5,6,1000);
        p1.printInfo();
        p1.move("downright");
        p1.printInfo();
        long beforeCollectBudget = p1.Budget();
        p1.collect(100);
        p1.printInfo();
        assertEquals(p1.Budget(),beforeCollectBudget-1); //for execute command -1
    }
    @Test
    void collectNoOwnerRegion(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,5,5,1000);
        p1.printInfo();
        p1.move("downright");
        p1.printInfo();
        long beforeCollectBudget = p1.Budget();
        p1.collect(100);
        p1.printInfo();
        assertEquals(p1.Budget(),beforeCollectBudget-1); //for execute command -1

    }
}
