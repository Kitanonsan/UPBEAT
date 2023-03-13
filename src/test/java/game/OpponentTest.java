package game;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class OpponentTest {

    @Test
    void UpOpponentTest(){
        for(int i = 0 ; i < Configuration.instance().m -1 ; i++){
            for(int j = 0 ; j < Configuration.instance().n ; j++){
                for(int k = 1 ; k < Configuration.instance().m-i ; k++){
                    Territory territory = new Territory();
                    Player p1 = new Player("P1",territory,Configuration.instance().m-1-i,j,0);
                    Player p2 = new Player("P2",territory,Configuration.instance().m-1-i-k,j,0);
                    System.out.println(Arrays.toString(p1.getPosition()));
                    System.out.println(Arrays.toString(p2.getPosition()));
                    System.out.println(p1.opponent());
                    assertEquals(k<=6?k:0,p1.opponent());
                }
            }
        }
    }
    @Test
    void DownOpponentTest(){
        for(int i = 0 ; i < Configuration.instance().m -1 ; i++){
            for(int j = 0 ; j < Configuration.instance().n ; j++){
                for(int k = 1 ; k < Configuration.instance().m-i ; k++){
                    Territory territory = new Territory();
                    Player p1 = new Player("P1",territory,i,j,0);
                    Player p2 = new Player("P2",territory,i+k,j,0);
                    System.out.println(Arrays.toString(p1.getPosition()));
                    System.out.println(Arrays.toString(p2.getPosition()));
                    System.out.println(p1.opponent());
                    assertEquals(k<=6?k:0,p1.opponent());
                }
            }
        }
    }

}
