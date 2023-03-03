package game;
import java.lang.Math;

public class Region {
    private final int[] position;
    private double deposit;
    private double r;
    private Player owner;
    private boolean isCenterCity;

    public Region(int row_position , int column_position, double start_deposit, double r){
        this.position = new int[2];
        position[0] = row_position;
        position[1] = column_position;
        this.deposit = start_deposit;
        this.r = r;
        this.owner = null;
        this.isCenterCity = false;
    }
    public void setOwner(Player player){
        this.owner = player;
    }

    public void setCenterCity(boolean b){
        this.isCenterCity = b;
    }

    public void updateInterestRate(int base_interest , int turnCount){
        this.r = base_interest*(Math.log10(deposit*Math.log(turnCount)));
    }
    public void updateDeposit(){
        this.deposit = deposit+(deposit*r/100);
    }

    public void updateAfterInvest(int amount, Player player, int max_dep){
        if(this.deposit + amount <= max_dep)
            this.deposit = this.deposit + amount;
        else
            this.deposit = max_dep;
        if(this.owner == null){
            this.setOwner(player);
        }
    }

    public void updateAfterCollect(int amount){
        if(amount - this.deposit < 0)
            return;
        else if(amount - this.deposit == 0){
            this.deposit = 0;
            this.setOwner(null);
        }
        else
            this.deposit = this.deposit - amount;
    }

    public void gotShot(int amount){
        if(this.owner != null){
            if(this.deposit - amount < 0){
                this.deposit = this.deposit - amount;
                if(this.owner != null){
                    owner.removeRegion(this);
                }
                this.setOwner(null);
            }
            else
                this.deposit = this.deposit - amount;
        }
    }

    public void showInfo(){
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