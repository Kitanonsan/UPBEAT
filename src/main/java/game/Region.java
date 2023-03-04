package game;
import java.lang.Math;

public class Region {
    private final int[] position;
    private double deposit;
    private double r;
    private Player owner;
    private boolean isCenterCity;

    public Region(int row_position , int column_position){
        this.position = new int[2];
        position[0] = row_position;
        position[1] = column_position;
        this.deposit = Configuration.instance().start_deposit;
        this.r = Configuration.instance().interest_pct;
        this.owner = null;
        this.isCenterCity = false;
    }
    public void setOwner(Player player){
        this.owner = player;
    }
    public void setCenterCityDeposit(int amount){this.deposit = amount;}
    public void setCenterCity(boolean b){
        this.isCenterCity = b;
    }
    public void updateInterestRate(int turnCount){
        if(turnCount == 1){
            this.r = Configuration.instance().interest_pct;
        }
        else{
            this.r = Configuration.instance().interest_pct*Math.log10(this.deposit)*Math.log(turnCount);
        }
    }
    public void updateDeposit(){
        this.deposit = deposit+(deposit*r/100);
    }
    public void updateAfterInvest(int amount, Player player){
        if(this.deposit + amount <= Configuration.instance().max_dep)
            this.deposit = this.deposit + amount;
        else
            this.deposit = Configuration.instance().max_dep;
        if(this.owner == null){
            this.setOwner(player);
            this.owner.addRegion(this);
        }
    }
    public int updateAfterCollect(int amount){
        if(amount - this.deposit < 0)
            return 0;
        else if(amount - this.deposit == 0){
            this.deposit = 0;
            this.r = Configuration.instance().interest_pct;
            this.owner.removeRegion(this);
            this.setOwner(null);
            return amount;
        }
        else{
            this.deposit = this.deposit - amount;
            return amount;
        }
    }
    public void gotShot(int amount){
        if(this.owner != null){
            if(this.deposit - amount < 0){
                this.deposit = 0;
                owner.removeRegion(this);
                this.setOwner(null);
                this.r = Configuration.instance().interest_pct;
            }
            else
                this.deposit = this.deposit - amount;
        }
    }
    public void printInfo(){
        System.out.print("(" + this.position[0] + "," + this.position[1] + ")");
        System.out.print(" Deposit : " + this.deposit);
        System.out.print(" Owner : " + this.owner);
        System.out.print(" Center city : " + (isCenterCity?this.owner:"no"));
        System.out.println(" ");
    }
    public int getDeposit(){
        int d = (int) this.deposit;
        return d;
    }
    public Player getOwner(){
        return this.owner;
    }
    public double getInterestRate(){
        return this.r;
    }

    public boolean isCenterCity() {
        return this.isCenterCity;
    }

}