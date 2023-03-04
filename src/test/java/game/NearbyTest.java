package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NearbyTest {
    @Test
    void UpNearbyTest(){
        for(int i = 0 ; i < Configuration.instance().m -1 ; i++){
            for(int j = 0 ; j < Configuration.instance().n ; j++){
                for(int k = 1 ; k < Configuration.instance().m-i ; k++){
                    Territory territory = new Territory();
                    Player p1 = new Player("P1",territory,Configuration.instance().m-1-i,j);
                    Player p2 = new Player("P2",territory,Configuration.instance().m-1-i-k,j);
                    p1.printPosition();
                    System.out.println(p1.nearby("up"));
                    assertEquals(100*k+Integer.toString(Configuration.instance().init_center_dep).length(),p1.nearby("up"));
                    System.out.println();
                }
            }
        }
    }

    @Test
    void DownNearbyTest(){
        for(int i = 0 ; i < Configuration.instance().m -1 ; i++){
            for(int j = 0 ; j < Configuration.instance().n ; j++){
                for(int k = 1 ; k < Configuration.instance().m-i ; k++){
                    Territory territory = new Territory();
                    Player p1 = new Player("P1",territory,i,j);
                    Player p2 = new Player("P2",territory,i+k,j);
                    p1.printPosition();
                    System.out.println(p1.nearby("down"));
                    assertEquals(100*k+Integer.toString(Configuration.instance().init_center_dep).length(),p1.nearby("down"));
                    System.out.println();
                }
            }
        }
    }

    @Test
    void UpRightNearbyTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,2,1);
        Player p2 = new Player("P2",territory,0,3);
        p1.printPosition();
        assertEquals(203,p1.nearby("upright"));
    }

    @Test
    void UpLeftNearbyTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,5,5);
        Player p2 = new Player("P2",territory,4,4);
        p1.printPosition();
        assertEquals(103,p1.nearby("upleft"));
    }

    @Test
    void DownRightNeabyTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,5,5);
        Player p2 = new Player("P2",territory,6,6);
        p1.printPosition();
        assertEquals(103,p1.nearby("downright"));
    }

    @Test
    void DownLeftNeabyTest(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,5,5);
        Player p2 = new Player("P2",territory,6,4);
        p1.printPosition();
        assertEquals(103,p1.nearby("downleft"));
    }

}
