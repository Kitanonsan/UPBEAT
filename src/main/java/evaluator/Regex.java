package evaluator;

import java.util.regex.Pattern;

public class Regex {
    public static String Regex = "([+*/%^\\-])|([0-9]+)|(=)\\b|([{()}])|(collect|done|down|downleft|downright|up|upleft|upright|invest|opponent|nearby|move|relocate|shoot|if|else|then|while)\\b|([a-zA-Z]+[a-zA-Z0-9]*)|([^ \\r\\n]+)";

    public static String Operator = "[+*/%^\\-]";
    public static String Number = "[0-9]+";
    public static String Assign = "=";
    public static String Parenthesis = "[{()}]";

    public static String Variable = "[a-zA-Z]+[a-zA-Z0-9]*";
    public static String ReservedWord = "collect|done|down|downleft|downright|up|upleft|upright|invest|opponent|nearby|move|relocate|shoot|if|else|then|while";

    public static String If = "if";
    public static String Then = "then";
    public static String Else = "else";

    public static String While = "while";

    public static String ActionCommand = "done|relocate|move|invest|collect|shoot";
    public static String Done = "done";
    public static String Relocate = "relocate";
    public static String Move = "move";
    public static String Shoot = "shoot";

    public static String RegionCommand = "invest|collect";
    public static String Invest = "invest";
    public static String Collect = "collect";

    public static String InfoExpr = "opponent|nearby";
    public static String Opponent = "opponent";
    public static String Nearby = "nearby";

    public static String Direction = "down|downleft|downright|up|upleft|upright";
    public static String Down = "down";
    public static String DownLeft = "downleft";
    public static String DownRight = "downright";
    public static String Up = "up";
    public static String UpLeft = "upleft";
    public static String UpRight = "upright";



    public static Pattern p_Regex = Pattern.compile(Regex);

    public static Pattern p_Operator = Pattern.compile(Operator);
    public static Pattern p_Number = Pattern.compile(Number);
    public static Pattern p_Assign = Pattern.compile(Assign);
    public static Pattern p_Parenthesis = Pattern.compile(Parenthesis);
    public static Pattern p_Variable = Pattern.compile(Variable);
    public static Pattern p_ReservedWord = Pattern.compile(ReservedWord);

    public static Pattern p_If = Pattern.compile(If);
    public static Pattern p_Then = Pattern.compile(Then);
    public static Pattern p_Else = Pattern.compile(Else);

    public static Pattern p_While = Pattern.compile(While);

    public static Pattern p_ActionCommand = Pattern.compile(ActionCommand);
    public static Pattern p_Done = Pattern.compile(Done);
    public static Pattern p_Move = Pattern.compile(Move);
    public static Pattern p_Shoot = Pattern.compile(Shoot);

    public static Pattern p_RegionCommand = Pattern.compile(RegionCommand);
    public static Pattern p_Invest = Pattern.compile(Invest);
    public static Pattern p_Collect = Pattern.compile(Collect);

    public static Pattern p_InfoExpr = Pattern.compile(InfoExpr);
    public static Pattern p_Opponent = Pattern.compile(Opponent);
    public static Pattern p_Nearby = Pattern.compile(Nearby);

    public static Pattern p_Direction = Pattern.compile(Direction);
    public static Pattern p_Down = Pattern.compile(Down);
    public static Pattern p_DownLeft = Pattern.compile(DownLeft);
    public static Pattern p_DownRight = Pattern.compile(DownRight);
    public static Pattern p_Up = Pattern.compile(Up);
    public static Pattern p_UpLeft = Pattern.compile(UpLeft);
    public static Pattern p_UpRight = Pattern.compile(UpRight);


}
