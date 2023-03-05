package game;

import org.junit.jupiter.api.Test;

public class PayTest {
    @Test
    void BudgetNotEnoughForMove(){
        Territory t = new Territory();
        Player p = new Player("P",t);
        p.move("up");
    }
}
