package generalTreeSearch.core.equationTree.operators.binary;

import generalTreeSearch.core.equationTree.EquationNode;

public abstract class BinaryOperator extends EquationNode {
    protected EquationNode left;
    protected EquationNode right;
    
    public BinaryOperator(EquationNode left, EquationNode right) {
	super();
	
	this.left = left;
	this.right = right;
    }
}
