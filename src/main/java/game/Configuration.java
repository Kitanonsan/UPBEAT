package game;
import UPBEAT.WebSocket.ConfigurationMessage;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.FileWriter;
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
        }
    }

    public static void writeConfiguration(ConfigurationMessage message){
        try {
            FileWriter writer = new FileWriter("src/configuration/config.txt");
            writer.write("m=" +message.getM() + "\n");
            writer.write("n=" + message.getN()+ "\n");
            writer.write("init_plan_min="+ message.getInit_plan_min()+ "\n");
            writer.write("init_plan_sec="+ message.getInit_plan_sec()+ "\n");
            writer.write("init_budget="+ message.getInit_budget()+ "\n");
            writer.write("init_center_dep="+ message.getInit_center_dep()+ "\n");
            writer.write("plan_rev_min="+ message.getPlan_rev_min()+ "\n");
            writer.write("plan_rev_sec="+ message.getPlan_rev_sec()+ "\n");
            writer.write("rev_cost="+ message.getRev_cost()+ "\n");
            writer.write("max_dep="+ message.getMax_dep()+ "\n");
            writer.write("interest_pct="+ message.getInterest_pct()+ "\n");
            writer.write("start_deposit="+ message.getStart_deposit()+ "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Configuration.instance().readConfiguration();
    }
}