package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvestTest {

    @Test
    public void investTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1" ,territory);
        p1.printInfo();
        p1.move("up");
        p1.invest(100);
        p1.printInfo();
        territory.printInfo();
        assertEquals(p1,territory.region(p1.getPosition()).getOwner());
    }

    @Test
    void investOnOpponentRegion(){
        Territory territory = new Territory();
        Player p1 = new Player("P1" ,territory ,7,7,1000);
        Player p2 = new Player("P2" ,territory ,6,9,1000);
        p2.move("downleft"); //p2 at 6,8
        p2.invest(100);// p2 invest
        p1.move("upright");
        p1.invest(100);
        p1.printInfo();
        p2.printInfo();
        assertEquals(p2,territory.region(new int[]{6,8}).getOwner()); // 6 ,8  is p1's region p2 can't invest here

    }

    @Test
    void investOnOpponentCenterCity(){
        Territory territory = new Territory();
        Player p1 = new Player("P1" ,territory ,7,7,1000);
        Player p2 = new Player("P2" ,territory ,6,8,1000);
        p1.move("upright");
        p1.invest(100);
        p1.printInfo();
        p2.printInfo();
        assertEquals(p2,territory.region(new int[]{6,8}).getOwner()); // 6,8 is p2's centerCity p1 can't invest here
    }
}
