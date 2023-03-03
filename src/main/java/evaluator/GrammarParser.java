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

    protected final Tokenizer tkz;
    Player player;
    public GrammarParser(String src, Player player) throws SyntaxError {
        this.tkz = new Tokenizer(src);
        this.player = player;
    }

    @Override
    public Node parse() throws SyntaxError {
        Node v = parsePlan();
        if (tkz.hasNextToken())
            throw new SyntaxError("leftover token");
        return v;
    }

    public Node parsePlan() throws SyntaxError{
        BlockStatementNode nodes = new BlockStatementNode();
        nodes.addStatement(parseStatement());
        while (tkz.hasNextToken()) {
            nodes.addStatement(parseStatement());
        }
        return nodes;
    }

    public Node parseStatement() throws SyntaxError{
        if (tkz.peek(Regex.If)){
            return parseIf();
        }else if (tkz.peek(Regex.While)){
            return  parseWhile();
        }else if (tkz.peek(Regex.Parenthesis)){
            return parseBlock();
        }else if (tkz.peek(Regex.ActionCommand) || tkz.peek(Regex.Variable)){
            return parseCommand();
        }else{
            throw new SyntaxError("Can not parse Statement");
        }
    }

    public Node parseIf() throws SyntaxError{
        tkz.consume(Regex.If);
        tkz.consume("[(]");
        Node Expr = parseExpression();
        tkz.consume("[)]");

        tkz.consume(Regex.Then);
        Node trueStatement = parseStatement();

//        System.out.println(tkz.peek());
        tkz.consume(Regex.Else);
        Node falseStatement = parseStatement();

        Node ifStatementNode = new IfStatementNode(Expr, trueStatement, falseStatement);
        return ifStatementNode;
    }

    public Node parseWhile() throws SyntaxError{
        tkz.consume(Regex.While);
        tkz.consume("[(]");
        Node Expr = parseExpression();
        tkz.consume("[)]");
        Node statement = parseStatement();

        Node whileStatement = new WhileStatementNode(Expr, statement);
        return whileStatement;
    }

    public Node parseBlock() throws SyntaxError{
//        System.out.println(tkz.peek());
        BlockStatementNode blockStatement = new BlockStatementNode();
        tkz.consume("[{]");
        while(!tkz.peek("[}]"))
            blockStatement.addStatement(parseStatement());
        tkz.consume("[}]");
        return blockStatement;
    }

    public Node parseCommand() throws SyntaxError {
        if (tkz.peek(Regex.ActionCommand)){
            return parseActionCommand();
        }else if (tkz.peek(Regex.Variable)){
            return parseAssignment();
        }else {
            throw new SyntaxError("Can not parse Command");
        }
    }
    //ActionCommand = "done|relocate|move|invest|collect|shoot";
    public Node parseActionCommand()throws SyntaxError {
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
        tkz.consume(Regex.Done);

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
        tkz.consume(Regex.Relocate);

        Node relocateCommandNode = new RelocateNode(player);
        return relocateCommandNode;
    }
    public Node parseRegion() throws SyntaxError{ //invest, collect
        String financeMode = tkz.consume();
        if (financeMode.matches(Regex.Invest)){
            Node amount = parseExpression();
            Node RegionCommandNode = new RegionCommandNode(financeMode, amount,player);
            return RegionCommandNode;
        }else if (financeMode.matches(Regex.Collect)){
            Node amount = parseExpression();
            Node RegionCommandNode = new RegionCommandNode(financeMode, amount,player);
            return RegionCommandNode;
        }else {
            throw new SyntaxError("Can not parse Region command");
        }
    }

    public Node parseAttack() throws SyntaxError{ //shoot
        tkz.consume(Regex.Shoot);
        String direction = parseDirection();
        Node expression = parseExpression();

        Node attackCommandNode = new AttackCommandNode(direction, player, expression);
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
                Node factor = parseFactor();
                if(factor.evaluate() == 0){
                    throw new EvalError("Cannot divide by zero");
                }else {
                    term = new BinaryArithExprNode(term, "/", factor);
                }
            }else if (op.equals("%")) {
                Node factor = parseFactor();
                if (factor.evaluate() == 0){
                    throw new EvalError("Cannot modulo by zero");
                }else{
                    term = new BinaryArithExprNode(term, "%", factor);
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
//        System.out.println(tkz.peek());
        if (tkz.peek((Regex.Number))){ // number
           return new NumberNode(Integer.parseInt(tkz.consume()));
        }else if(tkz.peek(Regex.InfoExpr)){ //info
            Node power = parseInfoExpression();
            return power;
        }else if (tkz.peek(Regex.Variable)){ // identifier
            IdentifierNode identifierNode = parseIdentifier();
            return identifierNode;
        }else if(tkz.peek("[(]")){
            tkz.consume("[(]");
            Node ExprNode = parseExpression();
            if (!tkz.peek("[)]")){
                throw new SyntaxError("unmatch -> ) ");
            }
            tkz.consume("[)]");
            return ExprNode;
        }else {
            throw  new SyntaxError("Can not parsePower");
        }
    }
    //    InfoExpression -> opponent | nearby Direction
    public Node parseInfoExpression() throws SyntaxError{
        String info = tkz.consume();
        if (info.matches(Regex.Opponent)){
            Node infoNode = new InfoExprNode(info, null, player); // TODO: fix that
            return infoNode;
        }else if(info.matches(Regex.Nearby)){
            String direction = parseDirection();
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
    public IdentifierNode parseIdentifier() throws SyntaxError {
        String identifier = tkz.peek();
        tkz.consume(Regex.Variable);

        IdentifierNode identifierNode = new IdentifierNode(identifier, player.getVariable());
        return identifierNode;
    }

}
