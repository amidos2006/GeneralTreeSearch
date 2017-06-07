package generalTreeSearch.core.equationTree.operators.unary;

import generalTreeSearch.core.equationTree.EquationNode;

public abstract class UnaryOperator extends EquationNode {
    protected EquationNode child;
    
    public UnaryOperator(EquationNode child) {
	super();
	
	this.child = child;
    }
}
