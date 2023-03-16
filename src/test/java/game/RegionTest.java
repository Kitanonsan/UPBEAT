package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegionTest {
    @Test
    void updateInterestRateTest(){
        Region r = new Region(0,0);
        for (int t = 2 ; t < 20 ; t++){
            double deposit = r.getDeposit();
            r.updateInterestRate(t);
            System.out.println("Turn : " + t +" Interest percent rate :" +r.getInterestRate());
            assertEquals(r.getInterestRate(),Configuration.instance().interest_pct*Math.log10(deposit)*Math.log(t));
        }
    }

    @Test
    void updateDepositTest(){
        Region r = new Region(0,0);
        r.printInfo();
        for(int i = 2 ; i < 10 ; i++){
            double deposit = r.getDeposit();
            r.updateDeposit();
            System.out.println(r.getDeposit());
        }
    }
}
