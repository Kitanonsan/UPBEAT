package game;

import org.junit.jupiter.api.Test;

public class ConstructorTest {
    @Test
    void Constructor(){
        Territory territory = new Territory();
        Player p = new Player("Non",territory);
        p.printPosition();
        territory.showMapInfo();
    }
}
