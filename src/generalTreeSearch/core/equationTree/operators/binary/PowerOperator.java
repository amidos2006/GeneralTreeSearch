package generalTreeSearch.core.equationTree.operators.binary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class PowerOperator extends BinaryOperator{
    public PowerOperator(EquationNode left, EquationNode right) {
	super(left, right);
    }

    @Override
    public double evaluate(GameNode node) {
	return Math.pow(this.left.evaluate(node), this.right.evaluate(node));
    }

    @Override
    public String toString() {
	return "pow(" + this.left.toString() + "," + this.right.toString() + ")";
    }
}
