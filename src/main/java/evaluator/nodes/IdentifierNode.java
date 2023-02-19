package evaluator.nodes;

import java.util.Map;

public class IdentifierNode implements Node{
    protected Map<String, Long> variables = null;
    protected String identifier;

    public IdentifierNode(String identifier, Map<String, Long> variables){
        this.identifier = identifier;
        this.variables = variables;
    }
    @Override
    public long evaluate() {
        if (!variables.containsKey(identifier)){
            variables.put(identifier,0L);
        }
        return variables.get(identifier);
    }

    @Override
    public void print() {
        System.out.println("Identifier");
    }

    public void assignValue(Long value){
        variables.put(identifier, value);
    }
}
