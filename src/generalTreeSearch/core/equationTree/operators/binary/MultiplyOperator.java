package generalTreeSearch.core.equationTree.operators.binary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class MultiplyOperator extends BinaryOperator{
    public MultiplyOperator(EquationNode left, EquationNode right) {
	super(left, right);
    }

    @Override
    public double evaluate(GameNode node) {
	return this.left.evaluate(node) * this.right.evaluate(node);
    }
    
    @Override
    public String toString() {
	return "mul(" + this.left.toString() + "," + this.right.toString() + ")";
    }
}
