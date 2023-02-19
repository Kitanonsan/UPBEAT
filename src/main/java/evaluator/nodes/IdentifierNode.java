package evaluator.nodes;

import java.util.Map;

public class IdentifierNode implements Node{
    protected Map<String, Long> variables = null;
    protected String identifier;
    public IdentifierNode(Map<String, Long> variables, String identifier){
        this.variables = variables;
        this.identifier = identifier;
    }
    @Override
    public long evaluate() {
        if (!variables.containsKey(identifier)){
            variables.put(identifier,0L);
        }
        return variables.get(identifier);
    }

    public void assign(Long value){
        variables.put(identifier, value);
    }
}
