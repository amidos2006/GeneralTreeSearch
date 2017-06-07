package generalTreeSearch.core.equationTree.operators.binary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class LargerOperator extends BinaryOperator{
    public LargerOperator(EquationNode left, EquationNode right) {
	super(left, right);
    }

    @Override
    public double evaluate(GameNode node) {
	return this.left.evaluate(node) > this.right.evaluate(node)?1:0;
    }
    
    @Override
    public String toString() {
	return "lg(" + this.left.toString() + "," + this.right.toString() + ")";
    }
}
