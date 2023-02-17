package evaluator;

import evaluator.nodes.*;
import game.Player;

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
    Player player;
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
            }else if(v.matches("(collect|done|down|downleft|downright|up|upleft|upright|invest|opponent|nearby|move|relocate|shoot|if|else|then|while)")){ //reserved words
                if (v.matches("if")){ //todo -> opponent|nearby
                    blockStatement.addStatement(parseIf());
                }else if(v.matches("while")){
                    blockStatement.addStatement(parseWhile());
                }else if (v.matches("done")){ // done
                    blockStatement.addStatement(parseDone());
                }else if (v.matches("relocate")) { //relocate
                    blockStatement.addStatement(parseRelocate());
                }else if (v.matches("move")) { //move
                    blockStatement.addStatement(parseMove());
                }else if (v.matches("invest|collect")) { // invest collect
                    blockStatement.addStatement(parseRegion());
                }else if (v.matches("shoot")) { // shoot
                    blockStatement.addStatement(parseAttack());
                }
            }else if(v.matches("([a-zA-Z]+[a-zA-Z0-9]*)")){ //Assign here
                blockStatement.addStatement(parseAssignment());
            }else if (v.matches("[({})]")){ // blockstatement
                if (v.equals("(") || v.equals(")")){
                    throw new SyntaxError("{ <- tong ma kon");
                } else if (v.equals("{")) {
                    tkz.consume();
                    blockStatement.addStatement(parsePlan());
                    if (!tkz.equals("}")){
                        throw new SyntaxError("mai mee -> }");
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
       tkz.consume("=");
       Node Expr = parseExpression();

       Node assignmentNode = new AssignmentStatementNode(identifier, Expr);
       return assignmentNode;
    }

    public Node parseIf() throws SyntaxError{
        tkz.consume("if");
        tkz.consume("(");
        Node Expr = parseExpression();
        tkz.consume(")");
        tkz.consume("then");
        Node trueStatement = parsePlan();
        tkz.consume("else");
        Node falseStatement = parsePlan();

        Node ifStatement = new IfStatementNode(Expr, trueStatement, falseStatement);
        return ifStatement;
    }

    public Node parseWhile() throws SyntaxError{
        tkz.consume("while");
        tkz.consume("(");
        Node Expr = parseExpression();
        tkz.consume(")");
        Node statement = parseExpression();

        Node whileStatement = new WhileStatementNode(Expr, statement);
        return whileStatement;
    }
//        ActionCommand → done | relocate | MoveCommand | RegionCommand | AttackCommand
    public Node parseDone() throws SyntaxError{
        tkz.peek("done");

        Node doneCommand = new DoneNode(player);
        return doneCommand;
    }
    public Node parseMove() throws SyntaxError{
        String direction = parseDirection();
        tkz.peek("move");

        Node moveCommand =new MoveCommandNode(direction, player);
        return moveCommand;
    }

    public Node parseRelocate() throws SyntaxError{
        String direction = parseDirection();
        tkz.peek("relocate");

        Node relocateCommand = new RelocateNode(direction, player);
        return relocateCommand;
    }
    public Node parseRegion() throws SyntaxError{ //invest, collect
        String financeMode = tkz.consume();
        if (financeMode.matches("invest")){
            Node RegionCommand = new RegionCommandNode(financeMode, player);
            return RegionCommand;
        }else if (financeMode.matches("collect")){
            Node RegionCommand = new RegionCommandNode(financeMode, player);
            return RegionCommand;
        }else {
            throw new SyntaxError("unparseable");
        }
    }

    public Node parseAttack() throws SyntaxError{ //shoot
        String direction = parseDirection();
        tkz.consume("shoot");

        Node attackCommand = new AttackCommandNode(direction, player);
        return attackCommand;
    }
    //    Expression → Expression + Term | Expression - Term | Term
    public Node parseExpression() throws SyntaxError, EvalError {
        Node expression = parseTerm();
        while (tkz.peek("+") || tkz.peek("-")){
            String op = tkz.consume();
            if (op.equals("+")){
                expression = new BinaryArithExprNode(expression, "+" ,parseTerm());
            }else if (op.equals("-")) {
                expression = new BinaryArithExprNode(expression, "-", parseTerm());
            }else {
                throw new SyntaxError("parseExpression mai dai");
            }
        }
        return expression;
    }
    //Term → Term * Factor | Term / Factor | Term % Factor | Factor
    public Node parseTerm() throws SyntaxError, EvalError{
        Node term = parseFactor();
        while (tkz.equals("*") || tkz.equals("/") || tkz.equals("%")){
            String op = tkz.consume();
            if(op.equals("*")){
                term = new BinaryArithExprNode(term, "*", parseFactor());
            }else if (op.equals("/")) {
                if(parseFactor().evaluate() == 0){
                    throw new EvalError("Cannot divide by zero");
                }else {
                    term = new BinaryArithExprNode(term, "/", parseFactor());
                }
            }else if (op.equals("%")) {
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
    public Node parseFactor() throws SyntaxError, EvalError{
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
        if (tkz.peek(("[0-9]+"))){ // number
            Node numberNode = new NumberNode(Long.parseLong(tkz.consume()));
            tkz.consume();
            return numberNode;
        }else if (tkz.peek("([a-zA-Z]+[a-zA-Z0-9]*)")){ // identifier
            IdentifierNode identifierNode = parseIdentifier();
            return identifierNode;
        }else if(tkz.peek("opponent") || tkz.peek("nearby")){ //info
           Node power = parseInfoExpression();
           return power;
        }else if(tkz.peek().equals("(")){
            tkz.consume();
            Node ExprNode = parseExpression();
            if (!tkz.peek().equals(")")){
                throw new SyntaxError("unmatch -> ) ");
            }
            tkz.consume();
            return ExprNode;
        }else {
            throw  new SyntaxError("unParseAble");
        }
    }
    //    InfoExpression → opponent | nearby Direction
    private Node parseInfoExpression() throws SyntaxError{
        String info = tkz.consume();
        String direction = parseDirection();
        if (info.matches("opponent")){
            Node infoNode = new InfoExprNode(info, direction, player);
            return infoNode;
        }else if(info.matches("nearby")){
            Node infoNode = new InfoExprNode(info, direction, player);
            return infoNode;
        }else{
            throw new SyntaxError("unParseAble");
        }
    }

    public String parseDirection() throws SyntaxError{
        String direction = tkz.peek();
        tkz.consume("down|downleft|downright|up|upleft|upright");

        return direction;
    }
    private IdentifierNode parseIdentifier() throws SyntaxError {
        String identifier = tkz.peek();
        tkz.consume("([a-zA-Z]+[a-zA-Z0-9]*)");

        IdentifierNode identifierNode = new IdentifierNode(player.getValue(), identifier);
        return identifierNode;
    }


}
