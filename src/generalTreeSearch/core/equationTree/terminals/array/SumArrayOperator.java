package generalTreeSearch.core.equationTree.terminals.array;

import generalTreeSearch.core.gameTree.GameNode;

public class SumArrayOperator extends ArrayOperator{
    public SumArrayOperator(String variableName) {
	super(variableName);
    }

    @Override
    public double evaluate(GameNode node) {
	double[] values = node.getChildVariable(this.variableName);
	if(type == SIBLING){
	    values = node.getSibilingVariable(this.variableName);
	}
	double total = 0;
	for(int i=0; i<values.length; i++){
	    total += values[i];
	}
	return total;
    }

    @Override
    public String toString() {
	String result = "c_";
	if(type == SIBLING){
	    result = "s_";
	}
	return "sum(" + result + this.variableName + ")";
    }
}
