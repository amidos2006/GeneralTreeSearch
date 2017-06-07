package generalTreeSearch.core.equationTree.operators.binary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class MaxOperator extends BinaryOperator{
    public MaxOperator(EquationNode left, EquationNode right) {
	super(left, right);
    }

    @Override
    public double evaluate(GameNode node) {
	return Math.max(this.left.evaluate(node), this.right.evaluate(node));
    }

    @Override
    public String toString() {
	return "max(" + this.left.toString() + "," + this.right.toString() + ")";
    }
}
