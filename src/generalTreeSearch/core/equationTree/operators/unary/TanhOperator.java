package generalTreeSearch.core.equationTree.operators.unary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class TanhOperator extends UnaryOperator{
    public TanhOperator(EquationNode child) {
	super(child);
    }

    @Override
    public double evaluate(GameNode node) {
	return Math.tanh(this.child.evaluate(node));
    }

    @Override
    public String toString() {
	return "tanh(" + this.child.toString() + ")";
    }
}
