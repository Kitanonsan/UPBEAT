package game;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Random;

public class Player {
    private final String name;
    private int budget;
    protected Map<String, Long> variable;
    private int[] position = new int[2] ; //row: position[0] , column: position[1]
    private int[] city_position = new int[2];
    private Set<Region> possessRegion = new HashSet<>();
    private boolean isPlayerDone = false;
    private final Territory territory ;
    public Player(String name , Territory territory){
        this.name = name;
        this.territory = territory;
        //Random start position
        Random rand = new Random();
        int start_row = rand.nextInt(territory.row());
        int start_column = rand.nextInt(territory.column());
        while(territory.region(new int[]{start_row,start_column}).getOwner() != null){
            start_row = rand.nextInt(territory.row());
            start_column = rand.nextInt(territory.column());
        }
        position[0] = start_row;
        position[1] = start_column;
        //Start city center position
        city_position[0] = position[0];
        city_position[1] = position[1];
        territory.region(city_position).setCenterCity(true);
        territory.region(city_position).setOwner(this);
        territory.region(city_position).setCenterCityDeposit(Configuration.instance().init_center_dep);
        possessRegion.add(territory.region(city_position));
        //initial start budget
        this.budget = Configuration.instance().init_budget;
    }

    //For testing only (you can assign spawn position on territory)
    public Player(String name , Territory territory , int row , int column , int budget){
        this.name = name;
        this.territory = territory;
        position[0] = row;
        position[1] = column;
        city_position[0] = row;
        city_position[1] = column;
        territory.region(city_position).setCenterCity(true);
        territory.region(city_position).setOwner(this);
        territory.region(city_position).setCenterCityDeposit(Configuration.instance().init_center_dep);
        possessRegion.add(territory.region(city_position));
        this.budget = budget;
    }
    public void move(String direction){
        if(!isPlayerDone){
            if(pay(1)){
                if(direction.equals("up")) {
                    if(onTerritory(nextPosition(position,"up"))){
                        position = nextPosition(position,"up");
                    }
                }
                if (direction.equals("down")){
                    if(onTerritory(nextPosition(position,"down"))){
                        position = nextPosition(position,"down");
                    }
                }
                if(direction.equals("upright")){
                    if(onTerritory(nextPosition(position,"upright"))){
                        position = nextPosition(position,"upright");
                    }
                }
                if(direction.equals("upleft")){
                    if(onTerritory(nextPosition(position,"upleft"))){
                        position = nextPosition(position,"upleft");
                    }
                }
                if(direction.equals("downleft")) {
                    if(onTerritory(nextPosition(position,"downleft"))){
                        position = nextPosition(position,"downleft");
                    }
                }
                if (direction.equals("downright")){
                    if(onTerritory(nextPosition(position,"downright"))){
                        position = nextPosition(position,"downright");
                    }
                }
            }
        }
        this.printInfo();
    }
    public void randomMove(){
        if(!isPlayerDone){
            String[] direction = {"up" , "down" , "upright", "upleft" , "downleft" ,"downright"};
            Random rand = new Random();
            int n = rand.nextInt(direction.length);
            move(direction[n]);
        }
    }
    public void relocate(){
        if(!isPlayerDone){

        }
    }
    public void invest(int budget){

    }
    public void collect(){

    }
    public void shoot(String direction){

    }
    public int nearby(String direction){
        int[] nearbyOpponent = position;
        for(int i = 1 ; i < Configuration.instance().m*Configuration.instance().n ; i++){
            nearbyOpponent = nextPosition(nearbyOpponent,direction);
            if(isOpponentRegion(nearbyOpponent)){
                return 100*i+Integer.toString(territory.region(nearbyOpponent).getDeposit()).length();
            }
        }
        return 0;
    }
    public int opponent(){
        int[] upDirection = position;
        int[] downDirection = position;
        int[] upleftDirection= position;
        int[] uprightDirection= position;
        int[] downleftDirection = position;
        int[] downrightDirection= position;
        if(territory.region(position).getOwner() == this){
            for(int i = 1 ; i <= 6; i++){
                upDirection= nextPosition(upDirection,"up");
                if(isOpponentRegion(upDirection))
                    return i;
                downDirection = nextPosition(downDirection,"down");
                if(isOpponentRegion(downDirection))
                    return i;
                upleftDirection = nextPosition(upleftDirection,"upleft");
                if(isOpponentRegion(upleftDirection))
                    return i;
                uprightDirection = nextPosition(uprightDirection,"upright");
                if(isOpponentRegion(uprightDirection))
                    return i;
                downleftDirection = nextPosition(downleftDirection,"downleft");
                if(isOpponentRegion(downleftDirection))
                    return i;
                downrightDirection = nextPosition(downrightDirection,"downright");
                if(isOpponentRegion(downrightDirection))
                    return i;
            }
        }
        return 0;
    }
    public void done(){
        System.out.println(this.name + " is done.");
        isPlayerDone = true;
    }
    private boolean pay(int totalCost){
        if (budget - totalCost >= 0) {
            budget -= totalCost;
            return true;
        }
        else {
            System.out.println(this.name + " don't have enough budget to execute this command.");
            done();
            return false;
        }
    }

    public boolean isLoss() {
        return territory.region(city_position).getDeposit() == 0;
    }
    private boolean onTerritory(int[] position){
        return (position[0] >= 0) && (position[1] >=0) && (position[0] < territory.row()) && (position[1] < territory.column());
    }
    private boolean isOpponentRegion(int[] position){
        if(onTerritory(position))
            return  territory.region(position).getOwner() != null && territory.region(position).getOwner() != this;
        else
            return false;
    }
    public Map<String, Long> getVariable(){
        return variable;
    }
    public void removeRegion(Region r){
        possessRegion.remove(r);
    }
    public void addRegion(Region r){possessRegion.add(r);}

    public void printInfo(){
        System.out.println("Name: " + this.name + " |  Budget: " + this.budget + " |  Number of regions: " + possessRegion.size());
        System.out.println("Position: (" + position[0] +"," + position[1] + ")");
    }
    public int[] getPosition(){
        int[] Position = this.position;
        return Position;
    }
    public int[] getCityPosition(){
        int[] cityPosition= this.city_position;
        return cityPosition;
    }
    public int[] nextPosition(int[] currentPosition,String direction){
        int[] nextPosition = new int[2];
        if(direction.equals("up")){
            nextPosition[0] = currentPosition[0]-1;
            nextPosition[1] = currentPosition[1];
        }
        else if(direction.equals("down")){
            nextPosition[0] = currentPosition[0]+1;
            nextPosition[1] = currentPosition[1];
        }
        else if(direction.equals("upright")){
            if(currentPosition[1]%2 ==0){
                nextPosition[0] = currentPosition[0];
            }
            else{
                nextPosition[0] = currentPosition[0]-1;
            }
            nextPosition[1] = currentPosition[1]+1;
        }
        else if(direction.equals("upleft")){
            if(currentPosition[1]%2 ==0){
                nextPosition[0] = currentPosition[0];
            }
            else{
                nextPosition[0] = currentPosition[0]-1;
            }
            nextPosition[1] = currentPosition[1]-1;
        }
        else if(direction.equals("downright")){
            if(currentPosition[1]%2 != 0){
                nextPosition[0] = currentPosition[0];
            }
            else{
                nextPosition[0] = currentPosition[0]+1;
            }
            nextPosition[1] = currentPosition[1]+1;
        } else if (direction.equals("downleft")) {
            if(currentPosition[1]%2 != 0){
                nextPosition[0] = currentPosition[0];
            }
            else{
                nextPosition[0] = currentPosition[0]+1;
            }
            nextPosition[1] = currentPosition[1]-1;
        }
        return nextPosition;
    }

}