package generalTreeSearch.core.equationTree.operators.binary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class ModOperator extends BinaryOperator{
    public ModOperator(EquationNode left, EquationNode right) {
	super(left, right);
    }

    @Override
    public double evaluate(GameNode node) {
	return (int)(this.left.evaluate(node)) % (int)(this.right.evaluate(node));
    }

    @Override
    public String toString() {
	return "mod(" + this.left.toString() + "," + this.right.toString() + ")";
    }
}
