package generalTreeSearch.core.equationTree.operators.binary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class DivideOperator extends BinaryOperator{
    public DivideOperator(EquationNode left, EquationNode right) {
	super(left, right);
    }

    @Override
    public double evaluate(GameNode node) {
	return this.left.evaluate(node) / this.right.evaluate(node);
    }
    
    @Override
    public String toString() {
	return "divide(" + this.left.toString() + "," + this.right.toString() + ")";
    }
}
