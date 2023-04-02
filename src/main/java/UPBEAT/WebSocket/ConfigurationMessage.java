package UPBEAT.WebSocket;
import lombok.Getter;

@Getter
public class ConfigurationMessage {
    private int m;
    private int n;
    private int init_plan_min;
    private int init_plan_sec;
    private int init_budget;
    private int init_center_dep;
    private int plan_rev_min;
    private int plan_rev_sec;
    private int rev_cost;
    private int max_dep;
    private int interest_pct;
    private int start_deposit;
}
