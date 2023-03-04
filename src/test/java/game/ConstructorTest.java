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
        System.out.println(Arrays.toString(player.nextPosition(player.getPosition(),"up")));
        System.out.println(Arrays.toString(player.nextPosition(player.getPosition(),"down")));
        System.out.println(Arrays.toString(player.nextPosition(player.getPosition(),"upleft")));
        System.out.println(Arrays.toString(player.nextPosition(player.getPosition(),"upright")));
        System.out.println(Arrays.toString(player.nextPosition(player.getPosition(),"downleft")));
        System.out.println(Arrays.toString(player.nextPosition(player.getPosition(),"downright")));
        System.out.println();
        Player player1 = new Player("P1",territory,1,5,100);
        System.out.println(Arrays.toString(player1.nextPosition(player1.getPosition(),"up")));
        System.out.println(Arrays.toString(player1.nextPosition(player1.getPosition(),"down")));
        System.out.println(Arrays.toString(player1.nextPosition(player1.getPosition(),"upleft")));
        System.out.println(Arrays.toString(player1.nextPosition(player1.getPosition(),"upright")));
        System.out.println(Arrays.toString(player1.nextPosition(player1.getPosition(),"downleft")));
        System.out.println(Arrays.toString(player1.nextPosition(player1.getPosition(),"downright")));
    }
}
