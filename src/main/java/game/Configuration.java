package game;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
public class Configuration{
    public int m;
    public int n;
    public int init_plan_min;
    public int init_plan_sec;
    public int init_budget;
    public int init_center_dep;
    public int plan_rev_min;
    public int plan_rev_sec;
    public int rev_cost;
    public int max_dep;
    public int interest_pct;
    public int start_deposit;

    private static Configuration instance;

    private Configuration(){
        readConfiguration();
    }

    public static Configuration instance(){
        if(instance == null){
            instance = new Configuration();
        }
        return instance;
    }

    public void readConfiguration(){
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/configuration/config.txt");
            props.load(fis);
            fis.close();
        } catch (IOException e) {
            System.err.println("Failed to read config file: " + e.getMessage());
            return;
        }
        try {
            this.m = Integer.parseInt(props.getProperty("m"));
            this.n = Integer.parseInt(props.getProperty("n"));
            this.init_plan_min = Integer.parseInt(props.getProperty("init_plan_min"));
            this.init_plan_sec = Integer.parseInt(props.getProperty("init_plan_sec"));
            this.init_budget = Integer.parseInt(props.getProperty("init_budget"));
            this.init_center_dep = Integer.parseInt(props.getProperty("init_center_dep"));
            this.plan_rev_min = Integer.parseInt(props.getProperty("plan_rev_min"));
            this.plan_rev_sec = Integer.parseInt(props.getProperty("plan_rev_sec"));
            this.rev_cost = Integer.parseInt(props.getProperty("rev_cost"));
            this.max_dep = Integer.parseInt(props.getProperty("max_dep"));
            this.interest_pct = Integer.parseInt(props.getProperty("interest_pct"));
            this.start_deposit = Integer.parseInt(props.getProperty("start_deposit"));
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
            return;
        }
    }
}