package UPBEAT.WebSocket;
import lombok.Getter;

@Getter
public class PlayerBody {
    private String name;
    private String plan;
    public PlayerBody(String name , String plan){
        this.name = name;
        this.plan = plan;
    }
}
