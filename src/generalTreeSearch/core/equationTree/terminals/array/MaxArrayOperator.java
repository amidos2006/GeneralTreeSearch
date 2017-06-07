package generalTreeSearch.core.equationTree.terminals.array;

import generalTreeSearch.core.gameTree.GameNode;

public class MaxArrayOperator extends ArrayOperator{
    public MaxArrayOperator(String variableName) {
	super(variableName);
    }

    @Override
    public double evaluate(GameNode node) {
	double[] values = node.getChildVariable(this.variableName);
	if(type == SIBLING){
	    values = node.getSibilingVariable(this.variableName);
	}
	double maxValue = values[0];
	for(int i=1; i<values.length; i++){
	    if(values[i] > maxValue){
		maxValue = values[i];
	    }
	}
	return maxValue;
    }

    @Override
    public String toString() {
	String result = "c_";
	if(type == SIBLING){
	    result = "s_";
	}
	return "max(" + result + this.variableName + ")";
    }

}
