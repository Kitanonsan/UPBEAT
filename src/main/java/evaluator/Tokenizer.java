package evaluator;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isLetter;

public class Tokenizer {
    private String src;
    private String next;
    int pos;

    private Matcher matcher;
    public Tokenizer (String src) throws SyntaxError{
        this.src = src;
        matcher = Regex.p_Regex.matcher(src);
        pos = 0;
        computeNext();
    }

    public boolean hasNextToken(){
        return next != null;
    }

    public String peek(){
        if(!hasNextToken()){
            throw new NoSuchElementException("No more tokens");
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

    public void computeNext() {
        if (matcher.find()){ //Attempts to find the next subsequence of the input sequence that matches the pattern.
            next = matcher.group(); //group returns the input subsequence matched by the previous match.
            if (next.matches("(#.*+)|\\n")) {
                while (!Objects.equals(next, "\n")) {
                    if (!matcher.find())
                        return;
                    next = matcher.group();
                }
                computeNext();
            }
            return;
        }else{
            next = null;
        }
    }
    public boolean peek(String regex){
        if(!hasNextToken())
            return false;
        return peek().matches(regex);
    }
    public void consume(String regex) throws SyntaxError {
        if(peek(regex))
            consume();
        else
            throw new SyntaxError(regex + " : dose not match");
    }
}
