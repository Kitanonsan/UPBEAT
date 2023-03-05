package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegionTest {
    @Test
    void updateInterestRateTest(){
        Region r = new Region(0,0);
        for (int t = 1 ; t < 20 ; t++){
            r.updateInterestRate(t);
            System.out.println("Turn : " + t +" Interest percent rate :" +r.getInterestRate());
        }
    }

    @Test
    void updateDepositTest(){
        Region r = new Region(0,0);
        r.printInfo();
        System.out.println(r.getDeposit());
        System.out.println(r.getInterestRate());
        for(int i = 0 ; i < 10 ; i++){
            r.updateDeposit();
            System.out.println(r.getDeposit());

        }
    }
}
