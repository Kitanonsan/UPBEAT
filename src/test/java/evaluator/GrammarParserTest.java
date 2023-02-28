package evaluator;

import evaluator.nodes.Node;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


class GrammarParserTest {
    StringBuilder s;
    GrammarParser parser;
    Tokenizer tkz;
    Node parse;
    @Test
    public void ExpressionTest() throws SyntaxError {
        //Addition
        parser = new GrammarParser("1+ 2 +7");
        assertEquals(10,parser.parseExpression().evaluate());

        //Subtraction
        parser = new GrammarParser("77-8-0");
        assertEquals(69,parser.parseExpression().evaluate());

        //Multiplication
        parser = new GrammarParser("2*11*1");
        assertEquals(22, parser.parseExpression().evaluate());

        //Division
        parser = new GrammarParser("9/10/7");
        assertEquals(9/10/7, parser.parseExpression().evaluate());

        //Modulo
        parser = new GrammarParser("1%3%2%7");
        assertEquals(1%3%2%7, parser.parseExpression().evaluate());
    }

    @Test
    public void MoveTest() throws SyntaxError {
        parser = new GrammarParser("move up");
        assertEquals("{move up}",parser.parse().toString());

        parser = new GrammarParser("move downright");
        assertEquals("{move downright}", parser.parse().toString());
    }

    @Test
    public void AttackTest() throws SyntaxError{
        parser = new GrammarParser("shoot upleft cost");
        assertEquals("{shoot upleft cost}", parser.parse().toString());
    }

    @Test
    public void DoneTest() throws SyntaxError{
        parser = new GrammarParser("done");
        assertEquals("{done}", parser.parse().toString());
    }

//    @Disabled
    @Test
    public void RelocateTest() throws SyntaxError{
        parser = new GrammarParser("relocate");
        assertEquals("{relocate}", parser.parse().toString());
    }

    @Test
    public void InvestTest() throws SyntaxError{
        parser = new GrammarParser("invest 200");
        assertEquals("{invest 200}", parser.parse().toString());
    }

    @Test
    public void CollectTest() throws SyntaxError{
        parser = new GrammarParser("collect 150");
        assertEquals("{collect 150}", parser.parse().toString());
    }

    @Test
    public void BlockCommandTest() throws SyntaxError{
        parser = new GrammarParser("{}");
        assertEquals("{{}}", parser.parse().toString());
    }

    @Test
    public void WhileStatementTest() throws SyntaxError{
        parser = new GrammarParser("while (deposit) {if (deposit - 100) then collect 150 else {}}");
        assertEquals("{while (deposit) {if (deposit - 100) then collect 150 else {}}}", parser.parse().toString());
    }

    @Test
    public void IfStatement() throws SyntaxError{
        parser = new GrammarParser("if (deposit - 100) then collect 150 else {}");
        assertEquals("{if (deposit - 100) then collect 150 else {}}",parser.parse().toString());
    }

}