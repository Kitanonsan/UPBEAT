package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        long budgetBeforeRelocate = p1.Budget();
        int[] oldCityCenterPosition = p1.getCityPosition();
        p1.relocate();
        int[] newCityCenterPosition = p1.getCityPosition();
        assertEquals(budgetBeforeRelocate-1-(5*5+10),p1.Budget()); // -1 for execute command and shortest path distant = 5 then - (5*5+10)
        assertNull(territory.region(oldCityCenterPosition).getOwner()); // Old CityCenter Position shouldn't have an owner
        assertEquals(p1,territory.region(newCityCenterPosition).getOwner()); //new CenterCityPosition the owner should be p1
        assertTrue(newCityCenterPosition[0] == p1.getCityPosition()[0] && newCityCenterPosition[1] == p1.getCityPosition()[1]);
        assertTrue(territory.region(p1.getPosition()).isCenterCity());
    }

    @Test
    void RelocateOpponentCenterCity(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,6,7,1000);
        int[] oldCenterCityPosition = p1.getCityPosition();
        p1.printInfo();
        p1.move("downleft");
        p1.move("downleft");
        p1.move("downleft");
        p1.move("down");
        p1.move("down");
        Player p2 = new Player("P2",territory,9,4,1000);
        p1.relocate();
        p1.printInfo();
        p2.printInfo();
        assertEquals(p1,territory.region(oldCenterCityPosition).getOwner());
        assertEquals(p2,territory.region(new int[]{9,4}).getOwner());
    }

    @Test
    void RelocateOpponentRegion(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,5,3,1000);
        Player p2 = new Player("P2",territory,6,3,1000);
        int[] oldCenterCityPosition = p1.getCityPosition();
        p2.move("upleft");
        p2.invest(100);
        assertEquals(p2,territory.region(p2.getPosition()).getOwner());
        p1.move("downleft");
        p1.relocate();
        assertEquals(p1,territory.region(oldCenterCityPosition).getOwner());
        assertEquals(p2,territory.region(new int[] {5,2}).getOwner());

    }
}
