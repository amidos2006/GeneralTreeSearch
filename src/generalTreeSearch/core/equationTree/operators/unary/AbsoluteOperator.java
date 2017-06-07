package generalTreeSearch.core.equationTree.operators.unary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class AbsoluteOperator extends UnaryOperator{
    public AbsoluteOperator(EquationNode child) {
	super(child);
    }

    @Override
    public double evaluate(GameNode node) {
	return Math.abs(this.child.evaluate(node));
    }

    @Override
    public String toString() {
	return "abs(" + this.child.toString() + ")";
    }

}
