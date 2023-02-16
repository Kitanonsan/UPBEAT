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
        while (tkz!=null){
            String v = tkz.peek();
            if(v.matches("([0-9]+)") || v.matches("([+*/%^-])") || v.matches("down|downleft|downright|up|upleft|upright") || v.matches("nearby|opponent")){
                tkz.consume();
                throw new SyntaxError("mai chai stater word");
            }else if(v.matches("(collect|done|down|downleft|downright|up|upleft|upright|invest|opponent|nearby|move|relocate|shoot|if|else|then|while)")){
                if (v.matches("if")){
                    blockStatement.addStatement(parseIf());
                }else if(v.matches("while")){
                    blockStatement.addStatement(parseWhile());
                }else if (v.matches("move")){
                    blockStatement.addStatement(parseMove());
                }
            }else if(v.matches("([a-zA-Z]+[a-zA-Z0-9]*)")){
                blockStatement.addStatement(parseAssignment());
            }else if (v.matches("[({})]")){
                if (v.equals("(") || v.equals(")")){
                    throw new SyntaxError("{ ma kon");
                } else if (v.equals("{")) {
                    tkz.consume();
                    blockStatement.addStatement(parsePlan());
                    if (!tkz.equals("}")){
                        throw new SyntaxError("missing }");
                    }
                } else if (v.equals("}")) {
                    break;
                }
            }else throw new SyntaxError("Syntax error: " + v);

        }
        return blockStatement;
    }

    public Node parseAssignment() throws SyntaxError{
       IdentifierNode identifier = parseIdentifier();
       Node Expr = parseExpression();
       Node assignmentNode = new AssignmentStatementNode(identifier, Expr);
            return assignmentNode;
    }

    }

    public Node parseIf() throws SyntaxError{

    }

    public Node parseWhile() throws SyntaxError{

    }

    public Node parseMoveNode() throws SyntaxError{

    }

    public Node parseAttack() throws SyntaxError{

    }
    //    Expression → Expression + Term | Expression - Term | Term
    public Node parseExpression() throws SyntaxError, EvalError {
        Node expression = parseTerm();
        while (tkz.peek("+") || tkz.peek("-")){
            String op = tkz.consume();
            if (op.equals("+")){
                expression = new BinaryArithExprNode(expression, "+" ,parseTerm());
            } else if (op.equals("-")) {
                expression = new BinaryArithExprNode(expression, "-", parseTerm());
            }else {
                throw new SyntaxError("parse mai dai");
            }
        }
        return expression;
    }
    //Term → Term * Factor | Term / Factor | Term % Factor | Factor
    public Node parseTerm() throws SyntaxError{
        Node term = parseFactor();
        while (tkz.equals("*") || tkz.equals("/") || tkz.equals("%")){
            String op = tkz.consume();
            if(op.equals("*")){
                term = new BinaryArithExprNode(term, "*", parseFactor());
            } else if (op.equals("/")) {
                if(parseFactor().evaluate() == 0){
                    throw new EvalError("Cannot divide by zero");
                }else {
                    term = new BinaryArithExprNode(term, "/", parseFactor());
                }
            } else if (op.equals("%")) {
                if (parseFactor().evaluate() == 0){
                    throw new EvalError("Cannot modulo by zero");
                }else{
                    term = new BinaryArithExprNode(term, "%", parseFactor());
                }
            }else {
                throw new SyntaxError("parseTerm mai dai");
            }
        }
        return  term;
    }
//    Factor → Power ^ Factor | Power
    public Node parseFactor() throws SyntaxError{
        Node factor = parsePower();
        while (tkz.equals("^")){
            String op = tkz.consume();
            if (op.equals("^")){
                factor = new BinaryArithExprNode(factor, "^", parsePower());
            }else {
                throw new SyntaxError("parseFactor mai dai");
            }
        }
        return factor;
    }
//    Power → <number> | <identifier> | ( Expression ) | InfoExpression
    public Node parsePower() throws SyntaxError{

    }


}
