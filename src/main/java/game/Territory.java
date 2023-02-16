package game;

public class Territory {
    public Region[][] Territory;

    public Territory(int m , int n , double start_deposit , double r){
        Region[][] Territory = new Region[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                Territory[i][j] = new Region(i,j,start_deposit,r);
            }
        }
        this.Territory = Territory;
    }
}