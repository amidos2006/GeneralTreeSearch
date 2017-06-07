package generalTreeSearch.core.equationTree.operators.unary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class SigmoidOperator extends UnaryOperator{
    public SigmoidOperator(EquationNode child) {
	super(child);
    }

    @Override
    public double evaluate(GameNode node) {
	return 1.0 / (1.0 + Math.exp(-this.child.evaluate(node)));
    }

    @Override
    public String toString() {
	return "sigmoid(" + this.child.toString() + ")";
    }
}
