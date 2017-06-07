package generalTreeSearch.core.equationTree.operators.unary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;
import generalTreeSearch.core.gameTree.TreeVariableName;

public class ExpandedOperator extends UnaryOperator{
    public ExpandedOperator(EquationNode child) {
	super(child);
    }

    private boolean isFullyExpanded(GameNode node, int limit){
	if(limit == 0){
	    return true;
	}
	GameNode[] expanded = node.getExpandedChildren();
	if(expanded.length != node.getVariable(TreeVariableName.totalChildren.toString()) && node.getState().isTerminal() == 0){
	    return false;
	}
	boolean result = true;
	for(GameNode e:expanded){
	    result = result && isFullyExpanded(e, limit - 1);
	}
	return result;
    }
    
    @Override
    public double evaluate(GameNode node) {
	int limit = (int) this.child.evaluate(node);
	return isFullyExpanded(node, limit)?1:0;
    }

    @Override
    public String toString() {
	return "abs(" + this.child.toString() + ")";
    }

}
