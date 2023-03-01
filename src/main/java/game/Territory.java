package game;

public class Territory {
    int m ;
    int n;
    public Region[][] Regions;
    private Configuration config = Configuration.instance();

    public Territory(){
        this.m = config.m;
        this.n = config.n;
        Region[][] Territory = new Region[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                Territory[i][j] = new Region(i,j,config.start_deposit,config.interest_pct);
            }
        }
        this.Regions = Territory;
    }
    public Region region(int row, int column){
        return Regions[row][column];
    }

    public void showMapInfo(){
        for (int i = 0 ; i < m; i++) {
            System.out.println("Row " + i);
            for (int j = 0; j < n; j++) {
                this.Regions[i][j].showInfo();
            }
            System.out.println("----------------------------------------");
        }
    }

    public void printMap(){
        System.out.println("Territory");
        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(Regions[i][j].getOwner() != null){
                    System.out.print("C ");
                }
                else{
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }
}