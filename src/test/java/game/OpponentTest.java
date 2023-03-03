package game;

import org.junit.jupiter.api.Test;

public class OpponentTest {

    @Test
    void OpponentTesting(){
        for(int i = 0 ; i < 10 ; i++){
            Territory territory = new Territory();
            Player p1 = new Player("1",territory);
            Player p2 = new Player("2",territory);
            territory.printMap();
            p1.printPosition();
            p2.printPosition();
            System.out.println(p1.opponent());
            System.out.println(p2.opponent());
            System.out.println("");
        }
    }
}
