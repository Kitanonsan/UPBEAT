package evaluator;

import evaluator.nodes.*;
import game.Player;


public class GrammarParser implements Parser{

//    Plan -> Statement+
//    Statement -> Command | BlockStatement | IfStatement | WhileStatement
//    Command -> AssignmentStatement | ActionCommand
//    AssignmentStatement -> <identifier> = Expression
//    ActionCommand -> done | relocate | MoveCommand | RegionCommand | AttackCommand
//    MoveCommand -> move Direction
//    RegionCommand -> invest Expression | collect Expression
//    AttackCommand -> shoot Direction Expression
//    Direction -> up | down | upleft | upright | downleft | downright
//    BlockStatement -> { Statement* }
//    IfStatement -> if ( Expression ) then Statement else Statement
//    WhileStatement -> while ( Expression ) Statement
//    Expression -> Expression + Term | Expression - Term | Term
//    Term -> Term * Factor | Term / Factor | Term % Factor | Factor
//    Factor -> Power ^ Factor | Power
//    Power -> <number> | <identifier> | ( Expression ) | InfoExpression
//    InfoExpression -> opponent | nearby Direction

    protected Tokenizer tkz;
    Player player;
    public GrammarParser(String src) throws SyntaxError {
        this.tkz = new Tokenizer(src);
        player = new Player("Name");
    }

    @Override
    public Node parse() throws SyntaxError {
        Node v = parsePlan();
        if (tkz.hasNextToken())
            throw new SyntaxError("leftover token");
        return v;
    }

    public Node parsePlan() throws SyntaxError{
            Node statementNote = parseStatement();
            return statementNote;
//        BlockStatementNode blockStatement = new BlockStatementNode();
//        while (tkz.hasNextToken()){
//            String v = tkz.peek();
//            if(v.matches(Regex.Number) || v.matches(Regex.Operator) || v.matches(Regex.Direction) || v.matches(Regex.InfoExpr)){
//                tkz.consume();
//                throw new SyntaxError("wrong stater word");
//            }else if(v.matches(Regex.ReservedWord)){ //reserved words
//                if (v.matches(Regex.If)){ //todo -> opponent|nearby
//                    blockStatement.addStatement(parseIf());
//                }else if(v.matches(Regex.While)){
//                    blockStatement.addStatement(parseWhile());
//                }else if (v.matches(Regex.Done)){ // done
//                    blockStatement.addStatement(parseDone());
//                }else if (v.matches(Regex.Relocate)) { //relocate
//                    blockStatement.addStatement(parseRelocate());
//                }else if (v.matches(Regex.Move)) { //move
//                    blockStatement.addStatement(parseMove());
//                }else if (v.matches(Regex.Shoot)) { // shoot
//                    blockStatement.addStatement(parseAttack());
//                }else if (v.matches(Regex.RegionCommand)) { // invest collect
//                    blockStatement.addStatement(parseRegion());
//                }
//            }else if(v.matches(Regex.Variable)){ //Assign here
//                blockStatement.addStatement(parseAssignment());
//            }else if (v.matches(Regex.Parenthesis)){ // blockstatement
//                if (v.equals("(") || v.equals(")")){
//                    throw new SyntaxError("{ <- tong ma kon");
//                } else if (v.equals("{")) {
//                    tkz.consume();
//                    blockStatement.addStatement(parsePlan());
//                    if (!tkz.equals("}")){
//                        throw new SyntaxError("mai mee -> }");
//                    }
//                } else if (v.equals("}")) {
//                    break;
//                }
//            }else if (v.matches("\n")){
//                tkz.consume();
//            }
//            else throw new SyntaxError("Syntax error: " + v);
//
//        }
//        return blockStatement;
    }

    private Node parseStatement() throws SyntaxError{
        if (tkz.peek(Regex.If)){
            return parseIf();
        }else if (tkz.peek(Regex.While)){
            return  parseWhile();
        }else if (tkz.peek(Regex.Parenthesis)){
            return parseBlock();
        }else {
            return parseCommand();
        }
    }

    private Node parseIf() throws SyntaxError{
        tkz.consume(Regex.If);
        tkz.consume("[(]");
        Node Expr = parseExpression();
        tkz.consume("[)]");
        tkz.consume(Regex.Then);
        Node trueStatement = parsePlan();
        tkz.consume(Regex.Else);
        Node falseStatement = parsePlan();

        Node ifStatementNode = new IfStatementNode(Expr, trueStatement, falseStatement);
        return ifStatementNode;
    }

    private Node parseWhile() throws SyntaxError{
        tkz.consume(Regex.While);
        tkz.consume("[(]");
        Node Expr = parseExpression();
        tkz.consume("[)]");
        Node statement = parseExpression();

        Node whileStatement = new WhileStatementNode(Expr, statement);
        return whileStatement;
    }

    private Node parseBlock() throws SyntaxError{
        BlockStatementNode blockStatement = new BlockStatementNode();
        tkz.consume("{");
        blockStatement.addStatement(parseStatement());
        tkz.consume("}");
        return blockStatement;
    }

    private Node parseCommand() throws SyntaxError {
        if (tkz.peek(Regex.ActionCommand)){
            return parseActionCommand();
        }else{
            return parseAssignment();
        }
    }

    private Node parseActionCommand()throws SyntaxError { //ActionCommand = "done|relocate|move|invest|collect|shoot";
        if (tkz.peek(Regex.Done)){
            return parseDone();
        }else if (tkz.peek(Regex.Relocate)){
            return parseRelocate();
        }else if(tkz.peek(Regex.Move)){ //Move
            return parseMove();
        }else if (tkz.peek(Regex.Shoot)){ //Attack
            return parseAttack();
        }else if(tkz.peek(Regex.RegionCommand)){
            return parseRegion();
        }else {
            throw new SyntaxError("Can not parse ActionCommand");
        }
    }


    public Node parseAssignment() throws SyntaxError{
       IdentifierNode identifier = parseIdentifier();
       tkz.consume(Regex.Assign); // =
       Node Expr = parseExpression();

       Node assignmentNode = new AssignmentStatementNode(identifier, Expr);
       return assignmentNode;
    }

//        ActionCommand -> done | relocate | MoveCommand | RegionCommand | AttackCommand
    public Node parseDone() throws SyntaxError{
        tkz.peek(Regex.Done);

        Node doneCommandNode = new DoneNode(player);
        return doneCommandNode;
    }
    public Node parseMove() throws SyntaxError{
        tkz.consume(Regex.Move);
        String direction = parseDirection();

        Node moveCommandNode = new MoveCommandNode(direction, player);
        return moveCommandNode;
    }

    public Node parseRelocate() throws SyntaxError{
        String direction = parseDirection();
        tkz.peek(Regex.Relocate);

        Node relocateCommandNode = new RelocateNode(direction, player);
        return relocateCommandNode;
    }
    public Node parseRegion() throws SyntaxError{ //invest, collect
        String financeMode = tkz.consume();
        if (financeMode.matches(Regex.Invest)){
            Node RegionCommandNode = new RegionCommandNode(financeMode, player);
            return RegionCommandNode;
        }else if (financeMode.matches(Regex.Collect)){
            Node RegionCommandNode = new RegionCommandNode(financeMode, player);
            return RegionCommandNode;
        }else {
            throw new SyntaxError("Can not parse Region command");
        }
    }

    public Node parseAttack() throws SyntaxError{ //shoot
        tkz.consume(Regex.Shoot);
        String direction = parseDirection();

        Node attackCommandNode = new AttackCommandNode(direction, player);
        return attackCommandNode;
    }
    //    Expression -> Expression + Term | Expression - Term | Term
    public Node parseExpression() throws SyntaxError, EvalError {
        Node expression = parseTerm();
        while (tkz.peek("\\+") || tkz.peek("\\-")){
            String op = tkz.consume();
            if (op.equals("+")){
                expression = new BinaryArithExprNode(expression, "+" ,parseTerm());
            }else if (op.equals("-")) {
                expression = new BinaryArithExprNode(expression, "-", parseTerm());
            }else {
                throw new SyntaxError("Can not parseExpression");
            }
        }
        return expression;
    }
    //Term -> Term * Factor | Term / Factor | Term % Factor | Factor
    public Node parseTerm() throws SyntaxError, EvalError{
        Node term = parseFactor();
        while (tkz.peek("\\*") || tkz.peek("/") || tkz.peek("%")){
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
//    Factor -> Power ^ Factor | Power
    public Node parseFactor() throws SyntaxError, EvalError{
        Node power = parsePower();
        if (tkz.peek("\\^")){
            tkz.consume();
            Node arith = new BinaryArithExprNode(power, "^", parseFactor());
            return arith;
        }
        return power;
    }
//    Power -> <number> | <identifier> | ( Expression ) | InfoExpression
    public Node parsePower() throws SyntaxError{
        if (tkz.peek((Regex.Number))){ // number
            Node numberNode = new NumberNode(Long.parseLong(tkz.peek()));
            tkz.consume();
            return numberNode;
        }else if (tkz.peek(Regex.Variable)){ // identifier
            IdentifierNode identifierNode = parseIdentifier();
            return identifierNode;
        }else if(tkz.peek(Regex.InfoExpr)){ //info
           Node power = parseInfoExpression();
           return power;
        }else if(tkz.peek("[(]")){
            tkz.consume();
            Node ExprNode = parseExpression();
            if (!tkz.peek("[)]")){
                throw new SyntaxError("unmatch -> ) ");
            }
            tkz.consume();
            return ExprNode;
        }else {
            throw  new SyntaxError("Can not parsePower");
        }
    }
    //    InfoExpression -> opponent | nearby Direction
    private Node parseInfoExpression() throws SyntaxError{
        String info = tkz.consume();
        String direction = parseDirection();
        if (info.matches(Regex.Opponent)){
            Node infoNode = new InfoExprNode(info, direction, player);
            return infoNode;
        }else if(info.matches(Regex.Nearby)){
            Node infoNode = new InfoExprNode(info, direction, player);
            return infoNode;
        }else{
            throw new SyntaxError("unParseAble");
        }
    }

    public String parseDirection() throws SyntaxError{
        String direction = tkz.peek();
        tkz.consume(Regex.Direction); //"down|downleft|downright|up|upleft|upright");

        return direction;
    }
    private IdentifierNode parseIdentifier() throws SyntaxError {
        String identifier = tkz.peek();
        tkz.consume(Regex.Variable);

        IdentifierNode identifierNode = new IdentifierNode(identifier, player.getVariable());
        return identifierNode;
    }



}