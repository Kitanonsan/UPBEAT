package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NearbyTest {
    @Test
    void UpNearbyTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,5,7,0);
        Player p2 = new Player("P2",territory,3,7,0);
        assertEquals(203,p1.nearby("up"));
    }

    @Test
    void DownNearbyTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,3,9,0);
        Player p2 = new Player("P2",territory,8,9,0);
        assertEquals(503,p1.nearby("down"));

    }

    @Test
    void UpRightNearbyTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,4,10,0);
        Player p2 = new Player("P2",territory,3,13,0);
        assertEquals(303,p1.nearby("upright"));
    }

    @Test
    void UpLeftNearbyTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,6,11,0);
        Player p2 = new Player("P2",territory,3,5,0);
        assertEquals(603,p1.nearby("upleft"));
    }

    @Test
    void DownRightNeabyTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,2,8,0);
        Player p2 = new Player("P2",territory,5,13,0);
        assertEquals(503,p1.nearby("downright"));
    }

    @Test
    void DownLeftNeabyTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,3,10,0);
        Player p2 = new Player("P2",territory,4,9,0);
        assertEquals(103,p1.nearby("downleft"));
    }

}
