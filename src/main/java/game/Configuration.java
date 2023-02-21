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
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
            return;
        }

        System.out.println("m = " + m);
        System.out.println("n = " + n);
        System.out.println("init_plan_min = " + init_plan_min);
        System.out.println("init_plan_sec = " + init_plan_sec);
        System.out.println("init_budget = " + init_budget);
        System.out.println("init_center_dep = " + init_center_dep);
        System.out.println("plan_rev_min = " + plan_rev_min);
        System.out.println("plan_rev_sec = " + plan_rev_sec);
        System.out.println("rev_cost = " + rev_cost);
        System.out.println("max_dep = " + max_dep);
        System.out.println("interest_pct = " + interest_pct);

    }
}