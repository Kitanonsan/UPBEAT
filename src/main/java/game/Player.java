package game;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Random;

public class Player {
    public final String name;
    public int budget;
    protected Map<String, Long> variable;
    public int[] position = new int[2] ; //row: position[0] , column: position[1]
    public int[] city_position = new int[2];
    private Set<Region> possessRegion = new HashSet<>();
    public boolean isLoss = false;
    private final Territory territory ;
    public Player(String name , Territory territory){
        this.name = name;
        this.territory = territory;

        //Random start position
        Random rand = new Random();
        int start_row = rand.nextInt(territory.m);
        int start_column = rand.nextInt(territory.n);
        while(territory.region(start_row,start_column).getOwner() != null){
            start_row = rand.nextInt(territory.m);
            start_column = rand.nextInt(territory.n);
        }
        position[0] = start_row;
        position[1] = start_column;

        //Start city center position
        city_position[0] = start_row;
        city_position[1] = start_column;
        territory.region(start_row,start_column).setCenterCity(true);
        territory.region(start_row,start_column).setOwner(this);
        territory.region(start_row,start_column).setCenterCityDeposit(Configuration.instance().init_center_dep);
        possessRegion.add(territory.region(city_position[0],city_position[1]));

        //initial start budget
        this.budget = Configuration.instance().init_budget;

    }
    public void move(String direction){
        if(direction.equals("up")) {
            if (isOnTerritory(position[0] - 1, position[1])) {
                System.out.println(this.name + " is moving up.");
                position[0] -= 1;
            }
        }
        if (direction.equals("down")){
            if(isOnTerritory(position[0] +1, position[1])){
                System.out.println(this.name + " is moving down.");
                position[0] += 1;
            }
        }
        if(direction.equals("upright")){
            if(isOnTerritory(position[0]-1,position[1]+1)){
                System.out.println(this.name + " is moving upright.");
                position[0]-=1;
                position[1]+=1;
            }
        }
        if(direction.equals("upleft")){
            if(isOnTerritory(position[0]-1,position[1]-1)){
                System.out.println(this.name + " is moving upleft.");
                position[0]-=1;
                position[1]-=1;
            }
        }
        if(direction.equals("downleft")) {
            if (isOnTerritory(position[0] + 1,position[1] - 1)) {
                System.out.println(this.name + " is moving downleft.");
                position[0] += 1;
                position[1] -= 1;
            }
        }
        if (direction.equals("downright")){
            if(isOnTerritory(position[0]+1,position[1]+1)){
                System.out.println(this.name + " is moving downright.");
                position[0]+=1;
                position[1]+=1;
            }
        }
        this.printPosition();
    }
    public void randomMove(){
        String[] direction = {"up" , "down" , "upright", "upleft" , "downleft" ,"downright"};
        Random rand = new Random();
        int n = rand.nextInt(direction.length);
        move(direction[n]);
        printPosition();
    }
    public void relocate(int x , int y){

    }
    public void invest(int budget){

    }
    public void collect(){

    }
    public void nearby(String direction){

    }
    public int opponent(){
        int smallestDistance = Integer.MAX_VALUE;
        Region checkRegion = null;
        //up direction
        for(int i = 1 ; i <= 6 ;i ++){
            if(isOnTerritory(city_position[0]-i,city_position[1])){
                checkRegion = territory.region(city_position[0]-i,city_position[1]);
                if(checkRegion.getOwner() != null && checkRegion.getOwner() != this){
                    if(i <= smallestDistance){
                        smallestDistance = i;
                    }
                }
            }
        }
        //down direction
        for(int i = 1 ; i <= 6 ;i ++){
            if(isOnTerritory(city_position[0]+i,city_position[1])){
                checkRegion = territory.region(city_position[0]+i,city_position[1]);
                if(checkRegion.getOwner() != null && checkRegion.getOwner() != this){
                    if(i <= smallestDistance){
                        smallestDistance = i;
                    }
                }
            }
        }
        //upright direction
        for(int i = 1 ; i <= 6 ;i ++){
            if(isOnTerritory(city_position[0]-i,city_position[1]+i)){
                checkRegion = territory.region(city_position[0]-i,city_position[1]+i);
                if(checkRegion.getOwner() != null && checkRegion.getOwner() != this){
                    if(i <= smallestDistance){
                        smallestDistance = i;
                    }
                }
            }
        }
        //upleft direction
        for(int i = 1 ; i <= 6 ;i ++){
            if(isOnTerritory(city_position[0]-i,city_position[1]-i)){
                checkRegion = territory.region(city_position[0]-i,city_position[1]-i);
                if(checkRegion.getOwner() != null && checkRegion.getOwner() != this){
                    if(i <= smallestDistance){
                        smallestDistance = i;
                    }
                }
            }
        }
        //downright direction
        for(int i = 1 ; i <= 6 ;i ++){
            if(isOnTerritory(city_position[0]+i,city_position[1]+i)){
                checkRegion = territory.region(city_position[0]+i,city_position[1]+i);
                if(checkRegion.getOwner() != null && checkRegion.getOwner() != this){
                    if(i <= smallestDistance){
                        smallestDistance = i;
                    }
                }
            }
        }
        //downleft direction
        for(int i = 1 ; i <= 6 ;i ++){
            if(isOnTerritory(city_position[0]+i,city_position[1]-i)){
                checkRegion = territory.region(city_position[0]+i,city_position[1]-i);
                if(checkRegion.getOwner() != null && checkRegion.getOwner() != this){
                    if(i <= smallestDistance){
                        smallestDistance = i;
                    }
                }
            }
        }
        if(smallestDistance == Integer.MAX_VALUE)
            return 0;
        else
            return smallestDistance;
    }
    public void shoot(String direction){

    }
    public void done(){
        return;
    }

    //Check Function
    public boolean isLoss() {
        return territory.region(city_position[0],city_position[1]).getDeposit() == 0;
    }
    private boolean isOnTerritory(int row , int column){
        return (row >= 0) && (column >=0) && (row < territory.m) && (column < territory.n);
    }

    //Parser Function
    public Map<String, Long> getVariable(){
        return variable;
    }
    //Manage Region Function
    public void removeRegion(Region r){
        possessRegion.remove(r);
    }

    public void addRegion(Region r){possessRegion.add(r);}

    //Print Function
    public void printPosition(){
        System.out.println(this.name+" at "+"Row: " + position[0] + " Column: " + position[1]);
        for(int i = 0 ; i < territory.m ; i ++){
            for(int j = 0 ; j < territory.n; j++){
                if(i == position[0] && j == position[1]){
                    if(territory.region(i,j).getOwner() == this)
                        if(territory.region(i,j).isCenterCity())
                            System.out.print("[P] ");
                        else
                            System.out.print("(P) ");
                    else
                        System.out.print(" P  ");
                }
                else{
                    if(territory.region(i,j).getOwner() == this)
                        if(territory.region(i,j).isCenterCity())
                            System.out.print("[C] ");
                        else{
                            System.out.print("(+) ");
                        }
                    else{
                        if(territory.region(i,j).getOwner() != null && territory.region(i,j).getOwner() != this){
                            if(territory.region(i,j).isCenterCity())
                                System.out.print("{O} ");
                            else{
                                System.out.print("(O) ");
                            }
                        }else {
                            System.out.print(" -  ");
                        }
                    }
                }
            }
            System.out.println();
        }
    }
    public void printPlayerInfo(){
        System.out.println("Name : " + this.name + " Budget : " + this.budget);
    }

    //API Function
    public int[] getPosition(){
        int[] copyPosition = this.position;
        return copyPosition;
    }

}