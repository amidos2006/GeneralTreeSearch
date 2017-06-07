package generalTreeSearch.core.state;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public abstract class CustomState implements StateInterface{
    protected EquationNode equation;
    
    public CustomState(EquationNode eq){
	this.equation = eq;
    }
    
    @Override
    public double evaluateState(GameNode node){
	return this.equation.evaluate(node);
    }
}
