package game;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

    private static Configuration configuration;

    public Configuration(){
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
            m = Integer.parseInt(props.getProperty("m"));
            n = Integer.parseInt(props.getProperty("n"));
            init_plan_min = Integer.parseInt(props.getProperty("init_plan_min"));
            init_plan_sec = Integer.parseInt(props.getProperty("init_plan_sec"));
            init_budget = Integer.parseInt(props.getProperty("init_budget"));
            init_center_dep = Integer.parseInt(props.getProperty("init_center_dep"));
            plan_rev_min = Integer.parseInt(props.getProperty("plan_rev_min"));
            plan_rev_sec = Integer.parseInt(props.getProperty("plan_rev_sec"));
            rev_cost = Integer.parseInt(props.getProperty("rev_cost"));
            max_dep = Integer.parseInt(props.getProperty("max_dep"));
            interest_pct = Integer.parseInt(props.getProperty("interest_pct"));
            start_deposit = Integer.parseInt(props.getProperty("start_deposit"));
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
            return;
        }

    }

    public static Configuration instance(){
        if(configuration == null){
            Configuration configuration = new Configuration();
        }
        return configuration;
    }
}