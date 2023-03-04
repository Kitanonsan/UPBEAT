package game;

import org.junit.jupiter.api.Test;

public class OpponentTest {

    public void delay(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            return;
        }
    }

    @Test
    void UpOpponentTest(){
        for(int i = 0 ; i < Configuration.instance().m -1 ; i++){
            for(int j = 0 ; j < Configuration.instance().n ; j++){
                for(int k = 1 ; k < Configuration.instance().m-i ; k++){
                    Territory territory = new Territory();
                    Player p1 = new Player("P1",territory,Configuration.instance().m-1-i,j);
                    Player p2 = new Player("P2",territory,Configuration.instance().m-1-i-k,j);
                    p1.printPosition();
                    System.out.println("Opponent return : " + p1.opponent());
                    System.out.println();
                    delay();
                }
            }
        }
    }
    @Test
    void DownOpponentTest(){
        for(int i = 0 ; i < Configuration.instance().m -1 ; i++){
            for(int j = 0 ; j < Configuration.instance().n ; j++){
                for(int k = 1 ; k < Configuration.instance().m-i ; k++){
                    Territory territory = new Territory();
                    Player p1 = new Player("P1",territory,i,j);
                    Player p2 = new Player("P2",territory,i+k,j);
                    p1.printPosition();
                    System.out.println("Opponent return : " + p1.opponent());
                    System.out.println();
                    delay();
                }
            }
        }
    }
}
