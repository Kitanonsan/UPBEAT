package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NearbyTest {
    @Test
    void UpNearbyTest(){

    }

    @Test
    void DownNearbyTest(){

    }

    @Test
    void UpRightNearbyTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,1,5,0);
        Player p2 = new Player("P2",territory,0,6,0);
        assertEquals(103,p1.nearby("upright"));
    }

    @Test
    void UpLeftNearbyTest(){

    }

    @Test
    void DownRightNeabyTest(){

    }

    @Test
    void DownLeftNeabyTest(){

    }

}
