package UPBEAT.WebSocket;
import game.Configuration;
import lombok.Getter;

@Getter
public class ConfigurationMessage {
    private int m;
    private int n;
    private final int init_plan_min;
    private final int init_plan_sec;
    private int init_budget;
    private int init_center_dep;
    private final int plan_rev_min;
    private final int plan_rev_sec;
    private int rev_cost;
    private int max_dep;
    private int interest_pct;
    private int start_deposit;

    public ConfigurationMessage(){
        Configuration config = Configuration.instance();
        this.init_plan_min = config.getInit_plan_min();
        this.init_plan_sec = config.getPlan_rev_sec();
        this.plan_rev_min = config.getPlan_rev_min();
        this.plan_rev_sec = config.getPlan_rev_sec();
    }
}
