package game;

import org.junit.jupiter.api.Test;

public class ShootTest {
    @Test
    void ShootOwnCenterCity(){
        Territory territory = new Territory();
        Player p1 = new Player("P1",territory,5,5,1000);
        p1.move("upright");
        p1.shoot("downleft" ,  500);
        p1.printInfo();
        System.out.println(p1.isLose());
        territory.printInfo();
    }
}
