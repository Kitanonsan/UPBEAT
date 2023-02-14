package evaluator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.*;
import static java.lang.Character.isLetter;

public class Tokenizer {
    private String src;
    private String next;
    private int pos;

    private static Pattern p = Pattern.compile("([+*/%^-])|([0-9]+)|([0-9]+.[0-9]+)|(=)|([({})])|(collect|done|down|downleft|downright|up|upleft|upright|invest|opponent|nearby|move|relocate|shoot|if|else|then|while)");
    //operator | จำนวนเต็มอย่างน้อย1ตัว | ทศนิยมอย่างน้อย1ตัว | = | วงเล็บ (){} | reserved words
    private Matcher matcher;

    public Tokenizer (String src) throws SyntaxError{
        this.src = src;
        matcher = p.matcher(src);
        pos = 0;
        computeNext();
    }

    public boolean hasNextToken(){
        return next != null;
    }

    public String peek(){
        if(!hasNextToken()){
            throw  new NoSuchElementException("No more tokens");
        }
        return next;
    }

    public String consume() {
        if (!hasNextToken()){
            throw new NoSuchElementException("No more tokens");
        }
        String result = next;
        computeNext();
        return result;
    }

    private void computeNext() {
        if (matcher.find()){ //Attempts to find the next subsequence of the input sequence that matches the pattern.
            next = matcher.group(); //group returns the input subsequence matched by the previous match.
        }else{
            next = null;
        }
    }
    public boolean peek(String regex){
        if(!hasNextToken())
            return false;
        return peek().matches(regex);
    }
    public void consume(String regex ) throws SyntaxError {
        if(peek(regex))
            consume();
        else
            throw new SyntaxError(regex + " expected");
    }
}
