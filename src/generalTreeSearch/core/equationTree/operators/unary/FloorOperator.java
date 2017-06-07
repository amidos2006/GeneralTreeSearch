package generalTreeSearch.core.equationTree.operators.unary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class FloorOperator extends UnaryOperator{
    public FloorOperator(EquationNode child) {
	super(child);
    }

    @Override
    public double evaluate(GameNode node) {
	return Math.floor(this.child.evaluate(node));
    }

    @Override
    public String toString() {
	return "floor(" + this.child.toString() + ")";
    }

}
