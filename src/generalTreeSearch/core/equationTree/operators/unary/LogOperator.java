package generalTreeSearch.core.equationTree.operators.unary;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class LogOperator extends UnaryOperator{
    public LogOperator(EquationNode child) {
	super(child);
    }

    @Override
    public double evaluate(GameNode node) {
	return Math.log(this.child.evaluate(node));
    }

    @Override
    public String toString() {
	return "ln(" + this.child.toString() + ")";
    }

}
