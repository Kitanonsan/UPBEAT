package evaluator;

import evaluator.nodes.BlockStatementNode;
import evaluator.nodes.Node;

public class Parser {

//    Plan → Statement+
//    Statement → Command | BlockStatement | IfStatement | WhileStatement
//    Command → AssignmentStatement | ActionCommand
//    AssignmentStatement → <identifier> = Expression
//    ActionCommand → done | relocate | MoveCommand | RegionCommand | AttackCommand
//    MoveCommand → move Direction
//    RegionCommand → invest Expression | collect Expression
//    AttackCommand → shoot Direction Expression
//    Direction → up | down | upleft | upright | downleft | downright
//    BlockStatement → { Statement* }
//    IfStatement → if ( Expression ) then Statement else Statement
//    WhileStatement → while ( Expression ) Statement
//    Expression → Expression + Term | Expression - Term | Term
//    Term → Term * Factor | Term / Factor | Term % Factor | Factor
//    Factor → Power ^ Factor | Power
//    Power → <number> | <identifier> | ( Expression ) | InfoExpression
//    InfoExpression → opponent | nearby Direction

    protected Tokenizer tkz;
    public Parser(String src) throws SyntaxError {
        this.tkz = new Tokenizer(src);
    }

    public Node parsePlan() throws SyntaxError{
        BlockStatementNode blockStatement = new BlockStatementNode();
        return blockStatement;
    }

    public Node parseAssignment() throws SyntaxError{

    }

    public Node parseIf() throws SyntaxError{

    }

    public Node parseWhile() throws SyntaxError{

    }

    public Node parseMoveNode() throws SyntaxError{

    }

    public Node parseAttack() throws SyntaxError{

    }

    public Node parseExpression() throws SyntaxError{

    }
    //Term → Term * Factor | Term / Factor | Term % Factor | Factor
    public Node parseTerm() throws SyntaxError{

    }
//    Factor → Power ^ Factor | Power
    public Node parseFactor() throws SyntaxError{

    }
//    Power → <number> | <identifier> | ( Expression ) | InfoExpression
    public Node parsePower() throws SyntaxError{

    }


}
