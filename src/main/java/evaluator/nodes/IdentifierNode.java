package evaluator.nodes;

import java.util.Map;

public class IdentifierNode implements Node{
    private Map<String, Long> variables;
    private  String identifier;

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
    public void print(StringBuilder s) {
        s.append(identifier);
    }

    public void assignValue(Long value){
        variables.put(identifier, value);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        print(builder);
        return builder.toString();
    }
}
