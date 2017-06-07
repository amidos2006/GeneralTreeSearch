package generalTreeSearch.core.equationTree.terminals.array;

import java.util.Arrays;

import generalTreeSearch.core.gameTree.GameNode;

public class MedianArrayOperator extends ArrayOperator{
    public MedianArrayOperator(String variableName) {
	super(variableName);
    }

    @Override
    public double evaluate(GameNode node) {
	double[] values = node.getChildVariable(this.variableName);
	if(type == SIBLING){
	    values = node.getSibilingVariable(this.variableName);
	}
	Arrays.sort(values);
	return values[(int)(values.length / 2)];
    }

    @Override
    public String toString() {
	String result = "c_";
	if(type == SIBLING){
	    result = "s_";
	}
	return "median(" + result + this.variableName + ")";
    }
}
