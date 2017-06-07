package generalTreeSearch.core.equationTree.terminals.array;

import java.util.HashMap;
import java.util.Map.Entry;

import generalTreeSearch.core.gameTree.GameNode;

public class ModeArrayOperator extends ArrayOperator{
    public ModeArrayOperator(String variableName) {
	super(variableName);
    }

    @Override
    public double evaluate(GameNode node) {
	double[] values = node.getChildVariable(this.variableName);
	if(type == SIBLING){
	    values = node.getSibilingVariable(this.variableName);
	}
	HashMap<String, Integer> repetition = new HashMap<String, Integer>();
	for(int i=0; i<values.length; i++){
	    if(!repetition.containsKey(Double.toString(values[i]))){
		repetition.put(Double.toString(values[i]), 1);
	    }
	    repetition.put(Double.toString(values[i]), repetition.get(Double.toString(values[i])) + 1);
	}
	String name = "";
	int value = 0;
	for(Entry<String, Integer> e:repetition.entrySet()){
	    if(e.getValue() > value){
		name = e.getKey();
	    }
	}
	return repetition.get(name);
    }

    @Override
    public String toString() {
	String result = "c_";
	if(type == SIBLING){
	    result = "s_";
	}
	return "mode(" + result + this.variableName + ")";
    }

}
