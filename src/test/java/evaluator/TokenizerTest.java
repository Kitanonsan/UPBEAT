package evaluator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    @Test
    public void getting_Test () throws SyntaxError {
        Tokenizer tkz = new Tokenizer("if (budget) then invest 50");
        assertEquals(tkz.consume(), "if");
        assertEquals(tkz.consume(), "(");
        assertEquals(tkz.consume(), "budget");
        assertEquals(tkz.consume(), ")");
        assertEquals(tkz.consume(), "then");
        assertEquals(tkz.consume(), "invest");
        assertEquals(tkz.consume(), "50");
    }

    @Test
    public void neglectComment_test () throws SyntaxError {
        Tokenizer tkz = new Tokenizer("int var = 1 #the acutal \nvar = 2");
        assertEquals(tkz.consume(), "int");
        assertEquals(tkz.consume(), "var");
        assertEquals(tkz.consume(), "=");
        assertEquals(tkz.consume(), "1");
        assertEquals(tkz.consume(), "var");
        assertEquals(tkz.consume(), "=");
        assertEquals(tkz.consume(), "2");
    }
}