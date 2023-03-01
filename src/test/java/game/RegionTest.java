package game;

import org.junit.jupiter.api.Test;

public class RegionTest {
    Configuration config = Configuration.instance();
    Region r = new Region(0,0, config.start_deposit, config.interest_pct);

    @Test
    void updateInterestRateTest(){
        for (int t = 0 ; t < 20 ; t++){
            r.updateInterestRate(config.interest_pct, t);
            System.out.println(r.getInterestRate());
        }
    }
}
