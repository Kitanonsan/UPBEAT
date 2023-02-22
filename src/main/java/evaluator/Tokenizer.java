package evaluator;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isLetter;

public class Tokenizer {
    private String src;
    private String next;

    private Matcher matcher;
    public Tokenizer (String src) throws SyntaxError{
        this.src = src;
        matcher = Regex.p_Regex.matcher(src);
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
    public void consume(String regex) throws SyntaxError {
        if(peek().matches(regex))
            consume();
        else
            throw new SyntaxError(regex + " dose not match");
    }
}
