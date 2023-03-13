package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShootTest {
    @Test
    void ShootOwnCenterCity(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,5,5,1000);
        p1.move("upright");
        p1.invest(100);
        p1.shoot("downleft" ,  500);
        p1.printInfo();
        System.out.println(p1.lose());
        assertTrue(p1.lose());
    }

    @Test
    void ShootOpponentReion(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,5,5,10000);
        Player p2 = new Player("P2",territory,4,6,1000);
        p2.move("down");
        p2.invest(100);
        assertEquals(p2,territory.region(new int[]{5,6}).getOwner()); //p2 invest 5,6 region
        p1.shoot("downright",1000);//p1 shoot 5,6
        assertNull(territory.region(new int[]{5,6}).getOwner());
    }

    @Test
    void ShootOpponentCenterCity(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,6,6,10000);
        Player p2 = new Player("P2",territory,7,7,1000);
        assertFalse(p2.lose());
        p1.shoot("downright",1000);//p1 shoot 7,7
        assertTrue(p2.lose());

    }
}
