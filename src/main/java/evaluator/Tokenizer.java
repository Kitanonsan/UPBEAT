package evaluator;

import java.util.NoSuchElementException;

import static java.lang.Character.*;
import static java.lang.Character.isLetter;

public class Tokenizer {
    private String src;
    private String next;
    private int pos;

    public Tokenizer (String src) throws SyntaxError{
        this.src = src;
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

    public String consume(){
        if (!hasNextToken()){
            throw new NoSuchElementException("No more tokens");
        }
        String result = next;
        computeNext();
        return result;
    }

    private void computeNext() { //Not sure about this function
        StringBuilder s = new StringBuilder();

        while (pos < src.length() && isWhitespace(src.charAt(pos))) {
            pos++;
        }

        if (pos == src.length()) {
            next = null; return;
        }

        char c = src.charAt(pos);

        if (isDigit(c)) {
            s.append(c);
            for (pos++; pos < src.length() && isDigit(src.charAt(pos)); pos++) {
                s.append(src.charAt(pos));
            }
        } else if (isLetter(c)) {
            s.append(c);
            for (pos++; pos < src.length() && isLetter(src.charAt(pos)); pos++) {
                s.append(src.charAt(pos));
            }

        } else if (c == '+' || c == '(' || c == ')' || c == '-' || c == '*' || c == '/' || c == '%') {
            s.append(c);
            pos++;
        } else {
            throw new LexicalError("Unknown character:" + c);
        }
        next = s.toString();
    }
    public boolean peek(String s){
        if(!hasNextToken())
            return false;
        return peek().equals(s);
    }
    public void consume(String s ) throws SyntaxError {
        if(peek(s))
            consume();
        else
            throw new SyntaxError(s + " expected");
    }
}
