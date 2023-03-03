package game;

import org.junit.jupiter.api.Test;

public class ConstructorTest {
    @Test
    void ConstructorTesting(){
        Territory territory = new Territory();
        Player p = new Player("Non",territory);
        p.printPosition();
        territory.showMapInfo();
        System.out.println(territory.region(p.city_position[0],p.city_position[1]));
        System.out.println( territory.region(p.city_position[0],p.city_position[1]).getOwner());
    }
}
