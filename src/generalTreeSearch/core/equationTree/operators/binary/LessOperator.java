package generalTreeSearch.core.equationTree.operators.binary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class LessOperator extends BinaryOperator{
    public LessOperator(EquationNode left, EquationNode right) {
	super(left, right);
    }

    @Override
    public double evaluate(GameNode node) {
	return this.left.evaluate(node) < this.right.evaluate(node)?1:0;
    }

    @Override
    public String toString() {
	return "ls(" + this.left.toString() + "," + this.right.toString() + ")";
    }
}
