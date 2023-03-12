package game;

import org.junit.jupiter.api.Test;

public class RelocateTest {

    @Test
    void Relocate(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,6,7,1000);
        p1.printInfo();
        p1.move("downleft");
        p1.move("downleft");
        p1.move("downleft");
        p1.move("down");
        p1.move("down");
        p1.relocate();
        territory.printInfo();
        territory.region(p1.getCityPosition()).printInfo();
        p1.printInfo();
    }

    @Test
    void RelocateOpponentRegion(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,6,7,1000);
        p1.printInfo();
        p1.move("downleft");
        p1.move("downleft");
        p1.move("downleft");
        p1.move("down");
        p1.move("down");
        Player p2 = new Player("P2",territory,9,4,1000);
        p1.relocate();
        territory.printInfo();
        territory.region(p1.getCityPosition()).printInfo();
        p1.printInfo();
        p2.printInfo();
    }
}
