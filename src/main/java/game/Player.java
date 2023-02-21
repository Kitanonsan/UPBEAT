package game;
import java.util.Set;

public class Player {
    public String name;
    public int budget;
    public int[] position;
    public int[] city_position;
    public Set<Region> possessRegion;
    public boolean isDone;
    public boolean isLoss;
    public Territory map;
    public Player(String name,Territory map){
        this.name = name;
        this.map = map;
    }

    public void move(String direction){
        if(direction.equals("up")){
            System.out.println("move up");
        } else if (direction.equals("down")){
            System.out.println("move down");
        }
        else if(direction.equals("upleft")){
            System.out.println("move upleft");
        }
        else if(direction.equals("upright")){
            System.out.println("upright");
        }
        else if(direction.equals("downleft")){
            System.out.println("downleft");
        } else if (direction.equals("downright")){
            System.out.println("downright");
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
}