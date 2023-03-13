package evaluator.nodes;

import java.util.Map;
import java.util.Random;

public class RandomNode extends IdentifierNode{
    protected Random random = null;


    public RandomNode(String identifier, Map<String, Long> variables) {
        super(identifier, variables);

        random = new Random();
        identifier = "random";
    }

    @Override
    public long evaluate(){
        return random.nextInt(1000);
    }
}
