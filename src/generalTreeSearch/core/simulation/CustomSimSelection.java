package generalTreeSearch.core.simulation;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class CustomSimSelection implements SimulationSelectionInterface{
    private EquationNode equation;
    
    public CustomSimSelection(EquationNode eq){
	this.equation = eq;
    }
    
    @Override
    public double getActionValue(GameNode node, int nextAction, int currentIteration, double simValue) {
	try {
	    node.setVariable("nextAction", nextAction);
	    node.setVariable("simValue", simValue);
	    node.setVariable("currentIteration", currentIteration);
	    double value = this.equation.evaluate(node);
	    node.deleteVariable("currentIteration");
	    node.deleteVariable("nextAction");
	    node.deleteVariable("simValue");

	    return value;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return 0.0;
    }

}
