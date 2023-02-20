package game;

import java.util.Set;

public class Player {
    public int budget;
    public int[] position;
    public int[] city_position;
    public Set<Region> possessRegion;
    public boolean isDone;
    public boolean isLoss;
    public Player(){}

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

}