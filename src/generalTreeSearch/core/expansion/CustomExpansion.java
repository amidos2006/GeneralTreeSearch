package generalTreeSearch.core.expansion;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class CustomExpansion implements NodeExpansionInterface{
    private EquationNode equation;
    
    public CustomExpansion(EquationNode eq){
	this.equation = eq;
    }
    
    @Override
    public double getNodeExpansionValue(GameNode node, int expandedAction) {
	try{
	    node.setVariable("expandedAction", expandedAction);
	    double value = this.equation.evaluate(node);
	    node.deleteVariable("expandedAction");
	    return value;
	}
	catch(Exception e){
	    e.printStackTrace();
	}
	return 0.0;
    }

}
