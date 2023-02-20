package evaluator;

import java.util.regex.Pattern;

public class Regex {
    public static String Regex = "([+*/%^-])|([0-9]+)|(=)|([({})])|(collect|done|down|downleft|downright|up|upleft|upright|invest|opponent|nearby|move|relocate|shoot|if|else|then|while)|([a-zA-Z]+[a-zA-Z0-9]*)|(\n+)";
    public static Pattern p_Regex = Pattern.compile(Regex);
}
