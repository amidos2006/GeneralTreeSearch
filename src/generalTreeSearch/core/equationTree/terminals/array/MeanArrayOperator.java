package generalTreeSearch.core.equationTree.terminals.array;

import generalTreeSearch.core.gameTree.GameNode;
import generalTreeSearch.core.gameTree.TreeVariableName;

public class MeanArrayOperator extends SumArrayOperator{
    public MeanArrayOperator(String variableName) {
	super(variableName);
    }

    @Override
    public double evaluate(GameNode node) {
	TreeVariableName current = TreeVariableName.totalExpandedChildren;
	if(type == SIBLING){
	    current = TreeVariableName.totalSibling;
	}
	return super.evaluate(node) / node.getVariable(current.toString());
    }
    
    @Override
    public String toString() {
	String result = "c_";
	if(type == SIBLING){
	    result = "s_";
	}
        return "mean(" + result + this.variableName + ")";
    }
}
