package generalTreeSearch.core.selection;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class CustomSelection implements NodeSelectionInterface{
    private EquationNode equation;
    
    public CustomSelection(EquationNode eq) {
	this.equation = eq;
    }
    
    @Override
    public double getNodeSelectionValue(GameNode node) {
	return this.equation.evaluate(node);
    }

}
