package generalTreeSearch.core.equationTree.operators.binary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class SubtractOperator extends BinaryOperator{
    public SubtractOperator(EquationNode left, EquationNode right) {
	super(left, right);
    }

    @Override
    public double evaluate(GameNode node) {
	return this.left.evaluate(node) - this.right.evaluate(node);
    }

    @Override
    public String toString() {
	return "sub(" + this.left.toString() + "," + this.right.toString() + ")";
    }
}
