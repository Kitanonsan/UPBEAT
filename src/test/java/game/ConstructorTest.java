package game;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class ConstructorTest {
    @Test
    void ConstructorTesting(){
        Territory territory = new Territory();
        Player player = new Player("P1",territory,1,6,100);
        player.printInfo();
    }
}
