package evaluator;

import evaluator.nodes.Node;
import game.Player;
import game.Territory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


class GrammarParserTest {
    StringBuilder s;
    GrammarParser parser;
    Tokenizer tkz;
    Node parse;
    Player player;

    @BeforeEach
    public void beforeEach() {
        Territory territory = new Territory();
        player = new Player("Non", territory);
    }
    @Test
    public void ExpressionTest() throws SyntaxError {
        //Addition
        parser = new GrammarParser("1+ 2 +7", player);
        assertEquals(10,parser.parseExpression().evaluate());

        //Subtraction
        parser = new GrammarParser("77-8-0", player);
        assertEquals(69,parser.parseExpression().evaluate());

        //Multiplication
        parser = new GrammarParser("2*11*1", player);
        assertEquals(22, parser.parseExpression().evaluate());

        //Division
        parser = new GrammarParser("9/10/7", player);
        assertEquals(9/10/7, parser.parseExpression().evaluate());

        //Modulo
        parser = new GrammarParser("1%3%2%7", player);
        assertEquals(1%3%2%7, parser.parseExpression().evaluate());
    }

    @Test
    public void MoveTest() throws SyntaxError {
        parser = new GrammarParser("move up", player);
        assertEquals("{move up}",parser.parse().toString());

        parser = new GrammarParser("move downright", player);
        assertEquals("{move downright}", parser.parse().toString());
    }

    @Test
    public void AttackTest() throws SyntaxError{
        parser = new GrammarParser("shoot upleft cost", player);
        assertEquals("{shoot upleft cost}", parser.parse().toString());
    }

    @Test
    public void DoneTest() throws SyntaxError{
        parser = new GrammarParser("done", player);
        assertEquals("{done}", parser.parse().toString());
    }

//    @Disabled
    @Test
    public void RelocateTest() throws SyntaxError{
        parser = new GrammarParser("relocate", player);
        assertEquals("{relocate}", parser.parse().toString());
    }

    @Test
    public void InvestTest() throws SyntaxError{
        parser = new GrammarParser("invest 200", player);
        assertEquals("{invest 200}", parser.parse().toString());
    }

    @Test
    public void CollectTest() throws SyntaxError{
        parser = new GrammarParser("collect 150", player);
        assertEquals("{collect 150}", parser.parse().toString());
    }

    @Test
    public void BlockCommandTest() throws SyntaxError{
        parser = new GrammarParser("{}", player);
        assertEquals("{{}}", parser.parse().toString());
    }

    @Test
    public void WhileStatementTest() throws SyntaxError{
        parser = new GrammarParser("while (deposit) {if (deposit - 100) then collect 150 else {}}", player);
        assertEquals("{while (deposit) {if (deposit - 100) then collect 150 else {}}}", parser.parse().toString());
    }

    @Test
    public void IfStatement() throws SyntaxError{
        parser = new GrammarParser("if (deposit - 100) then collect 150 else {}", player);
        assertEquals("{if (deposit - 100) then collect 150 else {}}",parser.parse().toString());
    }

}