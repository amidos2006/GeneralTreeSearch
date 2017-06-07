package generalTreeSearch.core.simulation;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class CustomSimStop implements SimulationStopInterface{
    private EquationNode equation;
    
    public CustomSimStop(EquationNode eq){
	this.equation = eq;
    }
    
    
    @Override
    public double getStopProbability(GameNode node, int currentIteration, double simValue) {
	try {
	    node.setVariable("simValue", simValue);
	    node.setVariable("currentIteration", currentIteration);
	    double value = this.equation.evaluate(node);
	    node.deleteVariable("currentIteration");
	    node.deleteVariable("simValue");
	    return value;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	return 0.0;
    }

}
