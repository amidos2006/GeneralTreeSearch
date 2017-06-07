package generalTreeSearch.core.equationTree.operators.binary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class AddOperator extends BinaryOperator{
    public AddOperator(EquationNode left, EquationNode right) {
	super(left, right);
    }

    @Override
    public double evaluate(GameNode node) {
	return this.left.evaluate(node) + this.right.evaluate(node);
    }
    
    @Override
    public String toString() {
	return "sum(" + this.left.toString() + "," + this.right.toString() + ")";
    }
}
