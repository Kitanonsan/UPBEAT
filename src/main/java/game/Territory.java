package game;

public class Territory {
    int m ;
    int n;
    public Region[][] Territory;

    public Territory(int m , int n , double start_deposit , double r){
        this.m = m;
        this.n = n;
        Region[][] Territory = new Region[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                Territory[i][j] = new Region(i,j,start_deposit,r);
            }
        }
        this.Territory = Territory;
    }

    public void showMapInfo(){
        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.Territory[i][j].showInfo();
                System.out.print("  ");

            }
            System.out.println("");

        }
    }
}