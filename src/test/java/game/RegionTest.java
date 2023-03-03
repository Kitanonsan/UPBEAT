package game;

import org.junit.jupiter.api.Test;

public class RegionTest {

    @Test
    void updateInterestRateTest(){
        Region r = new Region(0,0, Configuration.instance().start_deposit, Configuration.instance().interest_pct);
        System.out.println(r.getInterestRate());
        for (int t = 0 ; t < 20 ; t++){
            r.updateInterestRate(t);
            System.out.println(r.getInterestRate());
        }
    }

    @Test
    void updateDepositTest(){
        Region r = new Region(0,0, Configuration.instance().start_deposit, Configuration.instance().interest_pct);
        r.showInfo();
        System.out.println(r.getDeposit());
        for(int i = 0 ; i < 10 ; i++){
            r.updateDeposit();
            System.out.println(r.getDeposit());
        }
    }
}
