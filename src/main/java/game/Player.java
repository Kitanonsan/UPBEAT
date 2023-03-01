package game;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Random;

public class Player {
    public String name;
    public int budget;
    protected Map<String, Long> variable;
    public int[] position = new int[2] ; //row: position[0] , column: position[1]
    public int[] city_position = new int[2];
    private Set<Region> possessRegion = new HashSet<>();
    public boolean isDone = false;
    public boolean isLoss = false;
    private Territory territory ;
    private Configuration config = Configuration.instance();
    public Player(String name , Territory territory){
        this.name = name;
        this.territory = territory;

        //Random start position
        Random rand = new Random();
        int start_row = rand.nextInt(config.m);
        int start_column = rand.nextInt(config.n);
        while(territory.Regions[start_row][start_column].getOwner() != null){
            start_row = rand.nextInt(config.m);
            start_column = rand.nextInt(config.n);
        }
        position[0] = start_row;
        position[1] = start_column;

        //Start city center position
        city_position[0] = start_row;
        city_position[1] = start_column;
        territory.region(start_row,start_column).setCenterCity(true);
        territory.region(start_row,start_column).setOwner(this);
        possessRegion.add(territory.region(city_position[0],city_position[1]));

        //initial start budget
        this.budget = config.init_budget;

    }

    private boolean isOnTerritory(int row , int column){
        return (row >= 0) && (column >=0) && (row < territory.m) && (column < territory.n);
    }

    public void move(String direction){
        if(direction.equals("up")) {
            if (isOnTerritory(position[0] - 1, position[1])) {
                System.out.println(this.name + "is moving up.");
                position[0] -= 1;
            }
        }
        if (direction.equals("down")){
            if(isOnTerritory(position[0] +1, position[1])){
                System.out.println(this.name + "is moving down.");
                position[0] += 1;
            }
        }
        if(direction.equals("upright")){
            if(isOnTerritory(position[0]-1,position[1]+1)){
                position[0]-=1;
                position[1]+=1;
            }
        }
//        else if(direction.equals("upleft")){
//            if(position[0]-1 >=0 && position[1]-1 >= 0){
//                position[0]-=1;
//                position[1]-=1;
//            }
//        }
//        else if(direction.equals("downleft")){
//            if(position[0]+1 < map.m && position[1]-1 >= 0){
//                position[0]+=1;
//                position[1]-=1;
//            }
//        } else if (direction.equals("downright")){
//            if(position[0]+1 < map.m && position[1]+1 < map.n){
//                position[0]+=1;
//                position[1]+=1;
//            }
        this.printPosition();
    }

    public void nearby(String direction){

    }

    public void relocate(int x , int y){

    }

    public void invest(int budget){

    }

    public void randomMove(){

    }

    public void shoot(String direction){

    }

    public void collect(){

    }

    public void opponent(String direction){

    }

    public void done(){
        return;
    }

    public Map<String, Long> getVariable(){
        return variable;
    }

     public void printPosition(){
        System.out.println(this.name+" at "+"row: " + position[0] + " column: " + position[1]);
        for(int i = 0 ; i < config.m ; i ++){
            for(int j = 0 ; j < config.n; j++){
                if(i == position[0] && j == position[1]){
                    System.out.print("P ");
                }
                else{
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    public void removeRegion(Region r){
        possessRegion.remove(r);
    }

    public int[] getPosition(){
        int[] copyPosition = this.position;
        return copyPosition;
    }

    public boolean isLoss() {
        return territory.region(city_position[0],city_position[1]).getDeposit() == 0;
    }
}