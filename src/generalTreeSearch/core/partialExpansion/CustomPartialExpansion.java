package generalTreeSearch.core.partialExpansion;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class CustomPartialExpansion implements PartialExpansionInterface{
    private EquationNode equation;
    
    public CustomPartialExpansion(EquationNode eq){
	this.equation = eq;
    }
    
    @Override
    public double getExpansionProbability(GameNode node) {
	return this.equation.evaluate(node);
    }

}
