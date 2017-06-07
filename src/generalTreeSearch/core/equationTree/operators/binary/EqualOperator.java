package generalTreeSearch.core.equationTree.operators.binary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class EqualOperator extends BinaryOperator{
    public EqualOperator(EquationNode left, EquationNode right) {
	super(left, right);
    }

    @Override
    public double evaluate(GameNode node) {
	return this.left.evaluate(node) == this.right.evaluate(node)?1:0;
    }
    
    @Override
    public String toString() {
	return "eq(" + this.left.toString() + "," + this.right.toString() + ")";
    }
}
