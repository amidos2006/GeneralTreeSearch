package generalTreeSearch.core.equationTree.terminals.array;

import generalTreeSearch.core.gameTree.GameNode;

public class RangeArrayOperator extends ArrayOperator{
    public RangeArrayOperator(String variableName) {
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
	double minValue = values[0];
	for(int i=1; i<values.length; i++){
	    if(values[i] < minValue){
		minValue = values[i];
	    }
	}
	return maxValue - minValue;
    }

    @Override
    public String toString() {
	String result = "c_";
	if(type == SIBLING){
	    result = "s_";
	}
	return "range(" + result + this.variableName + ")";
    }

}
