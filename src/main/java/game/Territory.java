package game;

public class Territory {
    private final int m ;
    private final int n;
    private Region[][] Regions;
    public Territory(){
        this.m = Configuration.instance().m;
        this.n = Configuration.instance().n;
        Region[][] Territory = new Region[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                Territory[i][j] = new Region(i,j);
            }
        }
        this.Regions = Territory;
    }
    public Region region(int[] position){
        return Regions[position[0]][position[1]];
    }
    public void printInfo(){
        for (int i = 0 ; i < m; i++) {
            System.out.println("Row " + i);
            for (int j = 0; j < n; j++) {
                this.Regions[i][j].printInfo();
            }
            System.out.println("");
        }
    }
    public int row(){
        return m;
    }
    public int column(){
        return n;
    }

}