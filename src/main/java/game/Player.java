package game;
import java.util.Map;
import java.util.Set;

public class Player {
    public String name;
    public int budget;
    protected Map<String, Long> variable;
    public int[] position; //row: postion[0] , column: postion[1]
    public int[] city_position;
    public Set<Region> possessRegion;
    public boolean isDone = false;
    public boolean isLoss = false;
    public Territory map;
    public Player(String name,Territory map){
        position = new int[2];
        position[0] = 0;
        position[1] = 0;
        this.name = name;
        this.map = map;
    }

    public void move(String direction){
        if(direction.equals("up")){
            if(position[0]-1 >=0){
                position[0]-=1;
            }

        } else if (direction.equals("down")){
            if(position[0]+1 < map.m){
                position[0]+=1;
            }
        }
        else if(direction.equals("upright")){
            if(position[0]-1 >=0 && position[1]+1 < map.n){
                position[0]-=1;
                position[1]+=1;
            }
        }
        else if(direction.equals("upleft")){
            if(position[0]-1 >=0 && position[1]-1 >= 0){
                position[0]-=1;
                position[1]-=1;
            }
        }
        else if(direction.equals("downleft")){
            if(position[0]+1 < map.m && position[1]-1 >= 0){
                position[0]+=1;
                position[1]-=1;
            }
        } else if (direction.equals("downright")){
            if(position[0]+1 < map.m && position[1]+1 < map.n){
                position[0]+=1;
                position[1]+=1;
            }
        }
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

     public void getPosition(){
        System.out.println("row: " + position[0] + " column: " + position[1]);
    }
}